package com.jivesoftware.betterbookmarks.doa;

import com.jivesoftware.base.database.dao.DAOException;
import com.jivesoftware.community.EntityDescriptor;

import java.util.Collection;
import java.util.List;

/**
 *  Handles CRUD operations to enable persisting bookmarks.
 */
public interface BetterBookmarkDOA {

    /**
     * Persists a bookmark to the backing store. The identifier, creation, and modification dates will be configured
     * before the bookmark is persisted.
     *
     * @param betterBookmarkBean the bookmark which is being persisted.
     * @throws IllegalArgumentException <tt>null</tt> or incomplete bookmarks are not permitted.
     * @throws com.jivesoftware.base.database.dao.DAOException if an error occurs persisting the bookmark.
     * @return the unique identifier of the newly created bean
     */
    long create(BetterBookmarkBean betterBookmarkBean) throws IllegalArgumentException, DAOException;

    /**
     * Persists a collection of bookmarks to the backing store.
     *
     * @param betterBookmarkBean the collection of bookmark beans which will be persisted.
     * @throws DAOException if an error occurs persisting the bookmark.
     * @return the unique identifiers for the newly created beans.
     */
    List<Long> create(Collection<BetterBookmarkBean> betterBookmarkBean) throws DAOException;

    /**
     * Returns the bookmark bean with the given unique identifier. If the bookmark does not exist, null will be
     * returned.
     *
     * @param id the unique, system-wide identifier for the bookmark bean which is to be returned.
     * @throws DAOException if an error occurs retrieving the bookmark.
     * @return the bookmark bean with the given unique identifier.
     */
    BetterBookmarkBean getById(long id);

    boolean deleteById(long id);







}
