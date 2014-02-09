package com.jivesoftware.managedbookmarks.doa;

import com.jivesoftware.managedbookmarks.impl.ManagedBookmarkImpl;

/**
 * This DAO provides methods to directly manipulate managedBookmark table. ManagedBookmarkManager should always be used
 * to modify managedBookmark data and maintain valid caches.
 */
public interface ManagedBookmarkDAO {
    /**
     * Add a new managedBookmark to the table. Should never be called directly, but go through the ManagedBookmarkManager.
     * @param managedBookmark object to be persisted
     * @return newly created ManagedBookmark
     */
    ManagedBookmarkImpl createManagedBookmark(ManagedBookmarkImpl managedBookmark);
}