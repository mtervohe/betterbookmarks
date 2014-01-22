package com.jivesoftware.betterbookmarks.action;

import com.jivesoftware.betterbookmarks.BetterBookmarksBrowseAction;
import com.jivesoftware.community.action.util.Decorate;
import com.jivesoftware.community.bookmarks.BookmarksBrowseAction;
import com.jivesoftware.community.bookmarks.rest.BookmarksViewBean;
import com.jivesoftware.community.web.struts.SetReferer;
import com.jivesoftware.betterbookmarks.rest.BetterBookmarksViewBean;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Sets up a view to show a user's bookmarks in SBS.
 */
@Decorate(false)
@SetReferer(false)
public class ProfileBetterBookmarksBrowseAction extends BookmarksBrowseAction {
    static Logger log = LogManager.getLogger(ProfileBetterBookmarksBrowseAction.class);

    public void setProfileBookmarksBrowseViewBean(BookmarksViewBean profileBookmarksBrowseViewBean) {
        System.out.println("ProfileBetterBookmarksBrowseAction called setProfileBookmarksBrowseViewBean");
        setBookmarksBrowseViewBean( profileBookmarksBrowseViewBean);
    }

    @Override
    public String getBrowseAction() {
        System.out.println("ProfileBetterBookmarksBrowseAction called getBrowseAction");
        return "betterbookmarks";
    }

    @Override
    public String execute() {
        System.out.println("ProfileBetterBookmarksBrowseAction called execute");
        model = getBrowseActionSupport().getPopulatedBrowseViewBean();
        return SUCCESS;
    }

    public String doAdd(){
        System.out.println("ProfileBetterBookmarksBrowseAction doAdd add");
        return "SUCCESS";
    }


}
