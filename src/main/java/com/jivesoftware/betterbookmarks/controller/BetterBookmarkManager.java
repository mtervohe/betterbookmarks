package com.jivesoftware.betterbookmarks.controller;

import com.jivesoftware.betterbookmarks.model.BetterBookmark;

import com.jivesoftware.base.UnauthorizedException;
import com.jivesoftware.base.User;
import com.jivesoftware.community.JiveManager;
import com.jivesoftware.community.JiveObject;

import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 * User: matthew.hernandez
 * Date: 9/26/13
 * Time: 12:14 AM
 * To change this template use File | Settings | File Templates.
 */
public interface BetterBookmarkManager extends JiveManager {

    /**
     * Adds a betterbookark for the given owner on behalf of the given creator. The subject and notes for the newly created
     * favorite will be <tt>null</tt>.
     *
     * @param creator the user which is creating the betterbookark.
     * @param markedObject the object being betterbookark.
     * @return the newly created betterbookark.
     * @throws com.jivesoftware.base.UnauthorizedException if the currently authenticated user is not permitted to
     * create betterbookark on behalf of the given creator or for the given owner.
     */

    BetterBookmark addBetterBookmark(User creator, JiveObject markedObject) throws UnauthorizedException;

    /**
     * Removes the given betterbookark.
     *
     * @param betterbookark the betterbookark which is to be removed.
     */
    void removeBetterBookmark(BetterBookmark betterbookark);

    /**
     * Returns an iterator over the listing of betterbookark related to the given user.
     *
     *
     * @param user the user which will determine the set of betterbookarks returned.
     * @return an iterator over the listing of betterbookarks related to the given filter.
     */
    Iterator<BetterBookmark> getUserBetterBookarks(User user);

}
