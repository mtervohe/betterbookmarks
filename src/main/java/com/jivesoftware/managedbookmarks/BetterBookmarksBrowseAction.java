package com.jivesoftware.managedbookmarks;

import com.jivesoftware.community.action.HasMetaDescription;
import com.jivesoftware.community.bookmarks.rest.BookmarksViewBean;
import com.jivesoftware.community.browse.AbstractBrowsableAction;
import com.jivesoftware.community.browse.BrowseActionSupport;
import com.jivesoftware.community.browse.rest.impl.BrowseViewBean;
import com.jivesoftware.community.browse.rest.impl.FilterGroupBean;
import com.jivesoftware.community.browse.rest.impl.ItemsViewBean;
import com.jivesoftware.community.favorites.rest.BookmarkService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
//import com.jivesoftware.managedbookmarks.rest.BetterBookmarksViewBean;
//import com.jivesoftware.managedbookmarks.rest.BetterBookmarkService;

import java.util.Set;

/**
 * Sets up a view to show an overview of the user's browsing bookmarks in SBS.
 *
 * @since Jive SBS 5.0
 */
public class BetterBookmarksBrowseAction extends AbstractBrowsableAction implements HasMetaDescription {

    static Logger log = LogManager.getLogger(BetterBookmarksBrowseAction.class);

    private BookmarksViewBean bookmarksBrowseViewBean;
    private BookmarkService bookmarkServiceImpl;

    public void setBookmarkServiceImpl(BookmarkService bookmarkServiceImpl) {
        System.out.println("BetterBookmarksBrowseAction called setBookmarkServiceImpl");
        this.bookmarkServiceImpl =  bookmarkServiceImpl;
    }

    public void setBookmarksBrowseViewBean(BookmarksViewBean bookmarksBrowseViewBean) {
        System.out.println("BetterBookmarksBrowseAction called setBookmarksBrowseViewBean");
        this.bookmarksBrowseViewBean = bookmarksBrowseViewBean;
    }

    public BrowseActionSupport getBrowseActionSupport() {
        return new BrowseActionSupport(this) {
            @Override
            protected BrowseViewBean getBaseBrowseViewBean() {
                System.out.println("BetterBookmarksBrowseAction called getBaseBrowseViewBean");
                if (null != bookmarksBrowseViewBean) {
                    //if user is anonymous or we're viewing someone else's profile, hide filter group
                    if (isGuest() || (targetUser != null && targetUser.getID() != getUser().getID())) {
                        bookmarksBrowseViewBean.setHideFilterGroup(true);
                    }
                    if (isPartner()) {
                        bookmarksBrowseViewBean.setAdvertizeBookmarklet(false);
                    }
                    bookmarksBrowseViewBean.setCommunityName(communityManager.getRootCommunity().getName());
                }
                return bookmarksBrowseViewBean;
            }

            @Override
            protected ItemsViewBean getItems() {
                System.out.println("BetterBookmarksBrowseAction called getItems");
                BrowseViewBean browseViewBean = getBaseBrowseViewBean();
                FilterGroupBean filterGroupBean = browseViewBean.getFilterGroupBean();
                Set<String> filterId = getEffectiveFilterIDs(filterGroupBean);
                String sortKey = getEffectiveSortKey(filterGroupBean);
                int sortOrder = getEffectiveSortOrder(filterGroupBean);
                return bookmarkServiceImpl.getBookmarks(getEffectiveUserID(), filterGroupBean.getName(), filterId, getQuery(), sortKey, sortOrder, getStart(),
                        getNumItems());
            }

            @Override
            protected String getFeedAction() {
                System.out.println("BetterBookmarksBrowseAction called getFeedAction");
                return "view-browse-bookmarks-feed";
            }

        };
    }

    @Override
    public String buildMetaDescription() {
        System.out.println("BetterBookmarksBrowseAction called ");
        String cn = communityManager.getRootCommunity().getName();
        return getText("browse.dir.bookmarks.meta.description", new String[]{cn});
    }
}
