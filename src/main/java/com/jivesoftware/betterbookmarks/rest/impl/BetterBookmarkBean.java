package com.jivesoftware.betterbookmarks.rest.impl;

import javax.xml.bind.annotation.XmlRootElement;

import com.google.common.collect.Sets;
import com.jivesoftware.community.JiveObject;
import com.jivesoftware.community.browse.rest.impl.AbstractItemBean;
import com.jivesoftware.community.browse.rest.impl.JiveObjectActivityInfoProviderImpl;
import com.jivesoftware.community.browse.rest.impl.JiveObjectBodySnippetProviderImpl;
import com.jivesoftware.community.browse.rest.impl.JiveObjectBookmarkInfoProviderImpl;
import com.jivesoftware.community.browse.rest.impl.JiveObjectIsVisibleToPartnerProviderImpl;
import com.jivesoftware.community.browse.rest.impl.JiveObjectItemBeanFollowInfoProviderImpl;
import com.jivesoftware.community.browse.rest.impl.JiveObjectLastActivityDateProviderImpl;
import com.jivesoftware.community.browse.rest.impl.JiveObjectPlaceProviderImpl;
import com.jivesoftware.community.browse.rest.impl.JiveObjectPrivacyProviderImpl;
import com.jivesoftware.community.browse.rest.impl.JiveObjectShareInfoProviderImpl;
import com.jivesoftware.community.browse.rest.impl.JiveObjectViewCountProviderImpl;
import com.jivesoftware.community.favorites.Favorite;
import com.jivesoftware.community.favorites.ObjectFavorite;
import com.jivesoftware.community.favorites.PopularFavorite;
import com.jivesoftware.community.favorites.external.ExternalURL;
import com.jivesoftware.community.favorites.rest.impl.BookmarkItemTagInfoProviderImpl;
import com.jivesoftware.community.util.SkinUtils;
import com.jivesoftware.community.web.JiveResourceResolver;
import com.jivesoftware.util.DateUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collection;
import java.util.Date;

/**
 * A REST service bean for the {@link com.jivesoftware.community.favorites.rest.BookmarkService}.
 *
 * @since Jive SBS 5.0
 */
@XmlRootElement(name="favorite")
public class BetterBookmarkBean extends AbstractItemBean {

    public static Collection<String> DEFAULT_BROWSE_VIEW_PROPERTIES = Sets.newHashSet(
            JiveObjectActivityInfoProviderImpl.PROPERTY_NAME,
            JiveObjectLastActivityDateProviderImpl.PROPERTY_NAME,
            BookmarkItemTagInfoProviderImpl.PROPERTY_NAME,
            JiveObjectItemBeanFollowInfoProviderImpl.PROPERTY_NAME,
            JiveObjectShareInfoProviderImpl.PROPERTY_NAME,
            JiveObjectBookmarkInfoProviderImpl.PROPERTY_NAME,
            JiveObjectBodySnippetProviderImpl.PROPERTY_NAME,
            JiveObjectPrivacyProviderImpl.PROPERTY_NAME,
            JiveObjectViewCountProviderImpl.PROPERTY_NAME,
            JiveObjectPlaceProviderImpl.PROPERTY_NAME,
            JiveObjectIsVisibleToPartnerProviderImpl.PROPERTY_NAME
    );

    private static final Logger log = LogManager.getLogger(BetterBookmarkBean.class);

    private String unfilteredSubject;
    private String body;
    private String creationTime;
    private boolean yourBookmarks;
    private boolean popularBookmarks;
    private int popularCount;
    private boolean canEdit;
    private int markedObjectType;
    private long markedObjectId;
    private long bookmarkCount;
    private String popularRangeKey;
    private String externalURL;

    public BetterBookmarkBean() {
    }

    public BetterBookmarkBean(Favorite favorite)
    {
        super(favorite);
        this.unfilteredSubject = favorite.getUnfilteredSubject();
        this.body = favorite.getPlainBody();
        Date creationDate = favorite.getCreationDate();
        DateUtils dateUtils = new DateUtils();
        this.creationTime = creationDate != null ? dateUtils.displayFriendly(creationDate.getTime(), 1) : "";
        ObjectFavorite markedObject = favorite.getObjectFavorite();
        populateBean(markedObject);
    }

    public BetterBookmarkBean(PopularFavorite popularFavorite) {

        super(popularFavorite.getObjectFavorite().getFavoritedObject());
        this.popularCount = popularFavorite.getEntryCount();
        this.popularBookmarks = true;
        populateBean(popularFavorite.getObjectFavorite());
    }

    private void populateBean(ObjectFavorite markedObject) {
        try {
            if (markedObject != null){
                this.bookmarkCount = markedObject.getFavoriteCount();
                JiveObject jo = markedObject.getFavoritedObject();
                if (jo != null) {
                    this.markedObjectType = jo.getObjectType();
                    this.markedObjectId = jo.getID();
                    this.link = JiveResourceResolver.getJiveObjectURL(jo);
                    if (jo instanceof ExternalURL){
                        this.externalURL = ((ExternalURL)jo).getURLAsString();
                    }
                }

                // Display the bookmarked content-type icon instead of the
                // bookmark icon.
                this.iconCss = SkinUtils.getJiveObjectCss(jo, -1);
            }
        }
        catch (Exception e) {
            log.warn(e.getMessage(), e);
        }
    }

    public String getUnfilteredSubject() {
        return unfilteredSubject;
    }

    public void setUnfilteredSubject(String unfilteredSubject) {
        this.unfilteredSubject = unfilteredSubject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getMarkedObjectType() {
        return markedObjectType;
    }

    public void setMarkedObjectType(int markedObjectType) {
        this.markedObjectType = markedObjectType;
    }

    public long getMarkedObjectId() {
        return markedObjectId;
    }

    public void setMarkedObjectId(long markedObjectId) {
        this.markedObjectId = markedObjectId;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public boolean getCanEdit() {
        return canEdit;
    }

    public void setCanEdit(boolean canEdit) {
        this.canEdit = canEdit;
    }

    public boolean isYourBookmarks() {
        return yourBookmarks;
    }

    public void setYourBookmarks(boolean yourBookmarks) {
        this.yourBookmarks = yourBookmarks;
    }

    public long getBookmarkCount() {
        return bookmarkCount;
    }

    public void setBookmarkCount(long bookmarkCount) {
        this.bookmarkCount = bookmarkCount;
    }

    public boolean isPopularBookmarks() {
        return popularBookmarks;
    }

    public void setPopularBookmarks(boolean popularBookmarks) {
        this.popularBookmarks = popularBookmarks;
    }

    public int getPopularCount() {
        return popularCount;
    }

    public void setPopularCount(int popularCount) {
        this.popularCount = popularCount;
    }

    public String getPopularRangeKey() {
        return popularRangeKey;
    }

    public void setPopularRangeKey(String popularRangeKey) {
        this.popularRangeKey = popularRangeKey;
    }

    public String getExternalURL() {
        return externalURL;
    }
}
