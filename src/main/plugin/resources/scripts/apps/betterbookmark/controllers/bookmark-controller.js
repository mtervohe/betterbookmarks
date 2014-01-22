/**
 * @depends path=/plugins/betterbookmarks/resources/scripts/apps/betterbookmark/views/managedBookmark-view.js
 * @depends path=/plugins/betterbookmarks/resources/scripts/apps/betterbookmark/views/addButton-view.js
 * @depends path=/plugins/betterbookmarks/resources/scripts/apps/betterbookmark/models/managedBookmark-model.js
 */

define('apps/betterbookmark/controllers/bookmark-controller',
    ['apps/betterbookmark/views/managedBookmark-view',
        'apps/betterbookmark/views/addButton-view',
        'apps/betterbookmark/models/managedBookmark-model'],
    function(ManagedBookmarkView, AddButtonView, ManagedBookmark){
    return jive.oo.Class.extend(function(protect) {

        protect.init = function() {


            /*inject input field with button*/
            this.addButtonView = this.createAddButtonView();
            this.addButtonView.render();
            this.bindEvent(this);

            /*render the folder column list*/
            this.managedBookmarkView = this.createManagedBookmarkView();
            this.refreshManagedBookmark();

        };

        protect.createManagedBookmarkView = function(){
            return new ManagedBookmarkView();
        };

        protect.refreshManagedBookmark = function(){
            var folders = JSON.parse(localStorage.folders);
            this.managedBookmarkView.render({folders:folders});
        };

        protect.createAddButtonView = function(){
            return new AddButtonView();
        };

        protect.bindEvent = function(bookmarkController){
            document.getElementById('add-folder-button').addEventListener('click', function(){
                var folders = JSON.parse(localStorage.folders);
                var foldersName = document.getElementById('folder-name').value;
                folders.push(new ManagedBookmark(foldersName));
                localStorage.folders = JSON.stringify(folders);
                bookmarkController.refreshManagedBookmark();
            }, true);
        };

    });
});

