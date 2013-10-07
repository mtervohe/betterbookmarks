package com.jivesoftware.betterbookmarks.model;

import com.jivesoftware.base.User;
import com.jivesoftware.community.JiveContainer;
import com.jivesoftware.community.JiveObject;
import com.jivesoftware.community.favorites.Favorite;
import com.jivesoftware.community.favorites.type.FavoritableType;

import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 * User: matthew.hernandez
 * Date: 10/1/13
 * Time: 6:08 PM
 * To change this template use File | Settings | File Templates.
 */
public interface ObjectBetterBookmark {
    /**
     * Returns the object which this favorite relates to.
     *
     * @return the object which this favorite relates to.
     */
    <T extends JiveObject> T getFavoritedObject();

    /**
     * Returns the subject for this favorite.
     *
     * @return the subject for this favorite.
     */
    String getSubject();

    /**
     * Returns true if users can change the subject for a favorite related to this object and false if they cannot.
     *
     * @return true if users can change the subject for a favorite of the given object and false if they cannot.
     */
    boolean getCanUserDefineSubject();

    /**
     * Returns the url to view the object that this favorite relates to. For instance, if this favorite relates to a
     * discussion, this will return the url to view the discussion. Also, if this favorite relates to an
     * {@link com.jivesoftware.community.favorites.external.ExternalURL} it will return the url which this favorite
     * realtes to.
     *
     * @return the url to view the object that this favorite relates to.
     */
    String getUrl();

    /**
     * Returns the landing page to view all meta-information pertaining to a favorite. In a default installation of
     * Jive SBS the only type with an independent landing page is an
     * {@link com.jivesoftware.community.favorites.external.ExternalURL}. If the type of object that this Favorite
     * relates does not have a landing page for information about the favorite, as is the case with discussions, blog posts,
     * and documents, this function will return the url for the piece of content.
     *
     * @return the landing page to view all meta-information pertaining to a favorite.
     */
    String getFavoriteUrl();

    /**
     * Returns the object type of the favorited object.
     *
     * @return the object type of the favorited object.
     */
    FavoritableType getType();

    /**
     * Returns the set of all user favorites for this object.
     *
     * @return the set of all user favorites for this object.
     */
    Iterator<Favorite> getFavorites();

    /**
     * Returns the author of the favorited object. This is a convenience method for retrieving the user who authored the
     * favorited content, if one does not exist then <tt>null</tt> will be returned.
     *
     * @return the author of the favorited content or <tt>null</tt> if there is no author.
     * @see #getFavoritedObject()
     */
    User getObjectAuthor();

    /**
     * Returns the container of the favorited object. This is a convenience method for retrieving the container in which
     * the favorited content resides, if one does not exist then <tt>null</tt> will be returned.
     *
     * @return the container of the favorited object or <tt>null</tt> if there is no author.
     */
    JiveContainer getObjectContainer();

    /**
     * Returns the number of entites which have favorited this object.
     *
     * @return the number of entities which have favorited this object.
     */
    long getFavoriteCount();

    Favorite getUserFavorite(User user);

    /**
     * Returns true if the object this relates to is valid and present in the system.
     *
     * @return true if the object this relates to is valid and present in the system.
     * @since Jive SBS 5.0.0
     */
    boolean isValid();
}
