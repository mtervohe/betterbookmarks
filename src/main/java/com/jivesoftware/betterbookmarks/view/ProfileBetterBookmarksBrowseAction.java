package com.jivesoftware.betterbookmarks.view;

import com.jivesoftware.community.bookmarks.BookmarksBrowseAction;
import com.jivesoftware.community.bookmarks.rest.BookmarksViewBean;
import com.jivesoftware.community.work.action.ProfileBookmarksBrowseAction;
import org.apache.log4j.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: matthew.hernandez
 * Date: 5/21/13
 * Time: 8:45 PM
 * To change this template use File | Settings | File Templates.
 */


public class ProfileBetterBookmarksBrowseAction extends ProfileBookmarksBrowseAction {

    public void setProfileBookmarksBrowseViewBean(BookmarksViewBean profileBookmarksBrowseViewBean) {
        setBookmarksBrowseViewBean(profileBookmarksBrowseViewBean);
    }

    @Override
    public String getBrowseAction() {
        System.out.println("getBrowseAction in ProfileBetterBookmarksBrowseAction called");
        return "betterbookmarks";
    }

    @Override
    public String execute() {
        System.out.println("execute in ProfileBetterBookmarksBrowseAction called");
        model = getBrowseActionSupport().getPopulatedBrowseViewBean();
        return SUCCESS;
    }

    /*
    //Get that logger in there!
    Logger log = Logger.getLogger(ProfileBetterBookmarksBrowseAction.class);

    //Variable to hold the user's name
    private String userName;

    @Override
    public String execute() {
        System.out.println("execute method called in ProfileBetterBookmarksBrowseAction");
        log.info("In the Action class for the HelloWorld plugin!");
        return SUCCESS;
    }

    public String getUserName() {
        log.debug("The user's username is " + getUser().getUsername());
        return getUser().getUsername();
    }
    */

}




/*


*/

