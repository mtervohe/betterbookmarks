package com.jivesoftware.betterbookmarks.rest.impl;

import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;
import com.google.common.collect.Sets;
import com.jivesoftware.base.UnauthorizedException;
import com.jivesoftware.base.User;
import com.jivesoftware.base.UserNotFoundException;
import com.jivesoftware.base.aaa.JiveAuthentication;
import com.jivesoftware.betterbookmarks.rest.impl.BetterBookmarkBean;
import com.jivesoftware.community.JiveGlobals;
import com.jivesoftware.community.JiveObject;
import com.jivesoftware.community.NotFoundException;
import com.jivesoftware.community.bookmarks.*;
import com.jivesoftware.community.browse.BrowseFilterContext;
import com.jivesoftware.community.browse.BrowseFilterManager;
import com.jivesoftware.community.browse.BrowsePerspective;
import com.jivesoftware.community.browse.filter.BrowseFilter;
import com.jivesoftware.community.browse.filter.ObjectTypeFilter;
import com.jivesoftware.community.browse.filter.PerspectiveFilter;
import com.jivesoftware.community.browse.filter.TagFilter;
import com.jivesoftware.community.browse.filter.group.BrowseFilterGroup;
import com.jivesoftware.community.browse.rest.ItemBeanBuilder;
import com.jivesoftware.community.browse.rest.impl.JiveObjectAuthorProviderImpl;
import com.jivesoftware.community.browse.rest.impl.JiveObjectBodySnippetProviderImpl;
import com.jivesoftware.community.browse.sort.BrowseSort;
import com.jivesoftware.community.browse.sort.RecentActivitySort;
import com.jivesoftware.community.browse.sort.SubjectSort;
import com.jivesoftware.community.dwr.RemoteSupport;
import com.jivesoftware.community.favorites.*;
import com.jivesoftware.community.favorites.authz.FavoriteEntitlementManager;
import com.jivesoftware.community.favorites.rest.BookmarkBean;
import com.jivesoftware.community.favorites.rest.BookmarkService;
import com.jivesoftware.community.favorites.rest.impl.BookmarkItemBeanBuilderImpl;
import com.jivesoftware.community.favorites.rest.impl.BookmarkItemsBean;
import com.jivesoftware.community.favorites.rest.impl.PopularBookmarkObject;
import com.jivesoftware.community.favorites.type.FavoritableType;
import com.jivesoftware.community.favorites.type.FavoritableTypeComparators;
import com.jivesoftware.community.objecttype.JiveObjectType;
import com.jivesoftware.community.user.rest.UserItemBean;
import com.jivesoftware.community.util.collect.JiveIterators;
import com.jivesoftware.util.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import com.jivesoftware.betterbookmarks.rest.BetterBookmarkService;

import java.util.*;

import static com.jivesoftware.community.webservices.rest.ErrorBuilder.*;
import static com.jivesoftware.community.webservices.rest.ErrorBuilder.ERROR_CODE_BOOKMARK_NOT_FOUND;

/**
 * Created with IntelliJ IDEA.
 * User: matthew.hernandez
 * Date: 10/8/13
 * Time: 4:51 PM
 * To change this template use File | Settings | File Templates.
 */
public class BetterBookmarkServiceImpl extends RemoteSupport implements BetterBookmarkService {

    private static final Logger log = LogManager.getLogger(BetterBookmarkServiceImpl.class);

    private FavoriteManager favoriteManager;
    private FavoriteActivityProvider favoriteActivityProvider;
    private FavoritePopularityProvider favoritePopularityProvider;
    private FavoriteSearchManager favoriteSearchManager;
    private FavoriteEntitlementManager favoriteEntitlementManager;
    private ItemBeanBuilder<Favorite, BookmarkBean> bookmarkItemBeanBuilder;
    private ItemBeanBuilder<PopularBookmarkObject, BookmarkBean> bookmarkPopularItemBeanBuilder;
    private Collection<String> bookmarkItemBeanProps = BookmarkBean.DEFAULT_BROWSE_VIEW_PROPERTIES;
    private String PROPERTY_BOOKMARK_BODY_SNIPPET_LENGTH = "bookmark.body.snippet.length";
    private int DEFAULT_BOOKMARK_BODY_SNIPPET_LENGTH = 120;
    private Collection<String> bookmarkMenuItemBeanProps = new LinkedList<String>();
    private BrowseFilterManager browseFilterManager;

    public void setFavoriteManager(FavoriteManager favoriteManager) {
        this.favoriteManager = favoriteManager;
    }

    public void setFavoriteActivityProvider(FavoriteActivityProvider favoriteActivityProvider) {
        this.favoriteActivityProvider = favoriteActivityProvider;
    }

    public void setFavoritePopularityProvider(FavoritePopularityProvider favoritePopularityProvider) {
        this.favoritePopularityProvider = favoritePopularityProvider;
    }

    public void setFavoriteSearchManager(FavoriteSearchManager favoriteSearchManager) {
        this.favoriteSearchManager = favoriteSearchManager;
    }

    public void setFavoriteEntitlementManager(FavoriteEntitlementManager favoriteEntitlementManager) {
        this.favoriteEntitlementManager = favoriteEntitlementManager;
    }

    public void setBookmarkItemBeanBuilder(ItemBeanBuilder<Favorite, BookmarkBean> bookmarkItemBeanBuilder) {
        this.bookmarkItemBeanBuilder = bookmarkItemBeanBuilder;
    }

    public void setBookmarkPopularItemBeanBuilder(
            ItemBeanBuilder<PopularBookmarkObject, BookmarkBean> bookmarkPopularItemBeanBuilder) {
        this.bookmarkPopularItemBeanBuilder = bookmarkPopularItemBeanBuilder;
    }

    public void setBrowseFilterManager(BrowseFilterManager browseFilterManager) {
        this.browseFilterManager = browseFilterManager;
    }

    public void setBookmarkItemBeanProps(Collection<String> bookmarkItemBeanProps) {
        this.bookmarkItemBeanProps = bookmarkItemBeanProps;
    }

    public BetterBookmarkBean createBetterBookmark(BetterBookmarkBean bookmark) {
        JiveObject markedObject;
        try {
            markedObject = jiveObjectLoader.getJiveObject(bookmark.getMarkedObjectType(), bookmark.getMarkedObjectId());
        }
        catch (NotFoundException e) {
            throw notFound(ERROR_CODE_CONTENT_NOT_FOUND, e.getMessage());
        }

        User creator = getEffectiveUser(-1L); // Gets the current user.
        User owner;
        UserItemBean ownerItemBean = (UserItemBean) bookmark.getProp().get(JiveObjectAuthorProviderImpl.PROPERTY_NAME);
        if (null == ownerItemBean) {
            owner = creator;
        }
        else {
            try {
                owner = userManager.getUser(Long.parseLong(ownerItemBean.getID()));
            }
            catch (UserNotFoundException e) {
                throw notFound(ERROR_CODE_USER_NOT_FOUND, e.getMessage());
            }
        }

        Favorite savedBookmark;
        try {
            savedBookmark = favoriteManager.addFavorite(creator, owner, markedObject, bookmark.getSubject(),
                    bookmark.getBody());
        }
        catch (UnauthorizedException e) {
            throw forbidden(e.getMessage());
        }

        // TODO: check for public/private request parameter
        favoriteEntitlementManager.setPublic(savedBookmark);

        return new BetterBookmarkBean(savedBookmark);
    }

    public BookmarkItemsBean getBookmarks(long userID, String filterGroupID, Collection<String> filterID, String query,
                                          String sortKey, int sortOrder, int start, int numResults) {
        Set<BookmarkBean> result = Sets.newLinkedHashSet();
        User user = getEffectiveUser(userID);
        User owner = null;
        BrowseSort browseSort = null;
        boolean hasNext = false;
        try {
            Set<FavoritableType> types = Sets.newTreeSet(FavoritableTypeComparators.IDENTIFIER);
            BrowseFilterContext.Builder context = new BrowseFilterContext.Builder();
            context.setUserID(userID);
            context.setFilterIDs(filterID);
            context.setSortKey(sortKey);
            context.setSortOrder(sortOrder);
            context.setQuery(query);
            BrowseFilterGroup filterGroup = browseFilterManager.getFilterGroup(filterGroupID, context.build());
            Set<BrowseFilter> filters = filterGroup.getAppliedFilters();
            BrowsePerspective perspective = BrowsePerspective.USER;
            Set<String> tags = Sets.newHashSet();
            for (BrowseFilter filter : filters) {
                if (filter instanceof PerspectiveFilter) {
                    perspective = ((PerspectiveFilter) filter).getPerspective();
                }
                if (filter instanceof ObjectTypeFilter) {
                    Set<Integer> objectTypeIDs = ((ObjectTypeFilter) filter).getObjectTypeIDs();
                    for (int objectTypeID : objectTypeIDs) {
                        JiveObjectType jot = objectTypeManager.getObjectType(objectTypeID);
                        if (jot != null && jot instanceof FavoritableType) {
                            FavoritableType ft = (FavoritableType) jot;
                            if (ft.isEnabled()) {
                                types.add((FavoritableType) jot);
                            }
                        }
                    }
                }
                if (filter instanceof TagFilter) {
                    tags = ((TagFilter) filter).getValues();
                }
            }
            if (types.isEmpty()) {
                // ensure at least one type is passed to the favorite manager.
                types = objectTypeManager.getObjectTypesByType(FavoritableType.class);
            }
            owner = perspective.equals(BrowsePerspective.USER) ? user : null;

            browseSort = filterGroup.getAppliedSort();

            SortField sortField = SortField.modification_date;
            if (browseSort instanceof SubjectSort) {
                sortField = SortField.lexographical;
            }

            SortOrder order = (sortOrder == BrowseSort.SortOrder.ASCENDING.getKey()) ? SortOrder.ascending
                    : SortOrder.descending;

            // handle popular bookmarks separately as they have a different object.
            if (browseSort instanceof BookmarkCountSort) {
                FavoritePopularityProvider.TimePeriod timePeriod = timePeriod((BookmarkCountSort) browseSort);

                Map<String, Object> additionalContext = new HashMap<String, Object>();
                additionalContext.put("timePeriod", timePeriod);
                Iterator<PopularBookmarkObject> popularBookmarks = Iterators.transform(
                        popularBookmarks(timePeriod, types), PopularBookmarkObject.transform());
                hasNext = loadResults(start, numResults, result, popularBookmarks, additionalContext,
                        bookmarkPopularItemBeanBuilder, bookmarkItemBeanProps);
            }
            else {
                // for default settings, go against favoriteManager
                Iterator<Favorite> bookmarks = loadBookmarks(query, owner, types, perspective, tags, sortField, order);

                // only display bookmark notes and edit link when viewing your links on the main bookmarks page
                Map<String, Object> additionalContext = new HashMap<String, Object>();
                if (!filters.isEmpty() && !(filterGroupID.equals("bookmarksMenu"))) {
                    additionalContext.put(BookmarkItemBeanBuilderImpl.YOUR_BOOKMARKS,
                            perspective.equals(BrowsePerspective.USER));
                    if (browseSort instanceof RecentActivitySort) {
                        additionalContext.put(BookmarkItemBeanBuilderImpl.ALL_BOOKMARKS_BY_ACTIVITY,
                                !perspective.equals(BrowsePerspective.USER));
                    }
                }
                if (bookmarks != null) {
                    // the manager returns all results so we need to skip through them to get to the correct start
                    hasNext = loadResults(start, numResults, result, bookmarks, additionalContext,
                            bookmarkItemBeanBuilder, filterGroupID.equals("bookmarksMenu") ? bookmarkMenuItemBeanProps
                            : bookmarkItemBeanProps);
                }
            }
        }
        catch (UnauthorizedException ue) {
            throw forbidden(ue.getMessage());
        }
        catch (Exception e) {
            log.error("Failed to get bookmarks for user " + userID, e);
            throw exception(e);
        }

        BookmarkItemsBean bean = new BookmarkItemsBean(result);
        bean.setPageNumber(numResults > 0 ? (start / numResults) + 1 : 1);
        bean.setPageSize(numResults);
        bean.setHasNext(hasNext);
        if (owner != null) {
            bean.setOwner(toUserBean(owner, getUser()));
        }
        bean.setSort(browseSort);
        return bean;
    }

    private <J extends JiveObject> boolean loadResults(int start, int numResults, Set<BookmarkBean> result,
                                                       Iterator<J> bookmarks, Map<String, Object> additionalContext,
                                                       ItemBeanBuilder<J, BookmarkBean> bookmarkItemBeanBuilder, Collection<String> props) {
        if (start > 0) {
            JiveIterators.skip(bookmarks, start);
        }

        if (additionalContext != null){
            int bodySnippetLength = JiveGlobals.getJiveIntProperty(PROPERTY_BOOKMARK_BODY_SNIPPET_LENGTH,
                    DEFAULT_BOOKMARK_BODY_SNIPPET_LENGTH);
            additionalContext.put(JiveObjectBodySnippetProviderImpl.ADDITIONAL_CONTEXT_LENGTH, bodySnippetLength);
        }

        Iterator<J> bookmarkPage = JiveIterators.copySilently(bookmarks, numResults);

        while (bookmarkPage.hasNext()) {
            J bookmark = bookmarkPage.next();
            result.add(bookmarkItemBeanBuilder.build(bookmark, authenticationProvider.getJiveUser(), props,
                    additionalContext));
        }

        return bookmarks.hasNext();
    }

    private Iterator<Favorite> loadBookmarks(String query, User owner, Set<FavoritableType> types,
                                             BrowsePerspective perspective, Set<String> tags, SortField sortField, SortOrder order) {
        Iterator<Favorite> bookmarks;
        if (StringUtils.isBlank(query) && perspective == BrowsePerspective.ALL) {
            bookmarks = loadGlobalBookmarks(types, tags, sortField);
        }
        else if (StringUtils.isBlank(query) && perspective == BrowsePerspective.USER) {
            bookmarks = loadUserBookmarks(owner, types, tags, sortField, order);
        }
        else {
            bookmarks = searchBookmarks(query, owner, types, tags, sortField, order);
        }
        return bookmarks;
    }

    private Iterator<PopularFavorite> popularBookmarks(FavoritePopularityProvider.TimePeriod timePeriod,
                                                       Set<FavoritableType> favoritableTypes) {
        return favoritePopularityProvider.getPopularFavoritesByType(timePeriod, favoritableTypes);
    }

    private Iterator<Favorite> loadGlobalBookmarks(Set<FavoritableType> types, Set<String> tags, SortField sortField) {
        if (tags.isEmpty()) {
            return favoriteActivityProvider.getFavorites(types, sortField);
        }
        else {
            return favoriteActivityProvider.getFavoritesByTags(tags, types);
        }
    }

    private Iterator<Favorite> loadUserBookmarks(User owner, Set<FavoritableType> types, Set<String> tags,
                                                 SortField sortField, SortOrder order) {
        if (tags.isEmpty()) {
            return favoriteManager.getUserFavorites(owner, types, sortField, order);
        }
        else {
            return favoriteManager.getUserFavoritesByTagNames(owner, types, tags, sortField, order);
        }
    }

    private Iterator<Favorite> searchBookmarks(String query, User owner, Set<FavoritableType> types, Set<String> tags,
                                               SortField sortField, SortOrder order) {
        Iterable<Favorite> bookmarks;
        if (tags.isEmpty()) {
            bookmarks = favoriteSearchManager.search(query, owner, types, sortField, order);
        }
        else {
            bookmarks = favoriteSearchManager.searchWithTags(query, owner, types, sortField, tags, order);
        }
        bookmarks = Iterables.filter(bookmarks, favoriteActivityProvider.getFavoriteActivityPredicate());
        return bookmarks.iterator();
    }

    public void deleteBookmark(long favoriteID) {
        JiveAuthentication auth = authenticationProvider.getAuthentication();
        if (auth != null && !auth.isAnonymous()) {
            Favorite bookmark = favoriteManager.getFavorite(favoriteID);
            if (bookmark != null) {
                favoriteManager.removeFavorite(bookmark);
            }
            else {
                throw notFound(ERROR_CODE_BOOKMARK_NOT_FOUND, "No bookmark found with ID: " + favoriteID);
            }
        }
    }

    private FavoritePopularityProvider.TimePeriod timePeriod(BookmarkCountSort countSort) {
        if (countSort instanceof BookmarkPopularPastDaySort) {
            return FavoritePopularityProvider.TimePeriod.today;
        }
        else if (countSort instanceof BookmarkPopularPastWeekSort) {
            return FavoritePopularityProvider.TimePeriod.this_week;
        }
        else if (countSort instanceof BookmarkPopularPastMonthSort) {
            return FavoritePopularityProvider.TimePeriod.this_month;
        }
        else if (countSort instanceof BookmarkPopularPastYearSort) {
            return FavoritePopularityProvider.TimePeriod.this_year;
        }
        return FavoritePopularityProvider.TimePeriod.today;
    }

}