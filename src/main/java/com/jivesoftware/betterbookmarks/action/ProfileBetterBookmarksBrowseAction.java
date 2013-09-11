package com.jivesoftware.betterbookmarks.action;

import org.apache.log4j.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: matthew.hernandez
 * Date: 5/21/13
 * Time: 8:45 PM
 * To change this template use File | Settings | File Templates.
 */


public class ProfileBetterBookmarksBrowseAction extends com.jivesoftware.community.work.action.ProfileBookmarksBrowseAction {

    @Override
    public String execute(){
        System.out.println("OVERRIDE execute");
        model = getBrowseActionSupport().getPopulatedBrowseViewBean();
        return SUCCESS;
    }

    @Override
    public String getBrowseAction() {
        System.out.println("OVERRIDE getBrowseAction");
        return "profile-bookmarks";
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

