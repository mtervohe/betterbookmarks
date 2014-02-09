package com.jivesoftware.managedbookmarks.controller;

//TO DO - Check Out: 'interface FavoriteManager'


import com.jivesoftware.base.UnauthorizedException;
import com.jivesoftware.base.User;
import com.jivesoftware.managedbookmarks.model.BetterBookmark;
import com.jivesoftware.community.JiveObject;

import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 * User: matthew.hernandez
 * Date: 9/25/13
 * Time: 11:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class BetterBookmarkManagerImpl implements BetterBookmarkManager{


    public void destroy() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public BetterBookmark addBetterBookmark(User creator, JiveObject markedObject) throws UnauthorizedException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void removeBetterBookmark(BetterBookmark betterbookark) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public Iterator<BetterBookmark> getUserBetterBookarks(User user) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}


