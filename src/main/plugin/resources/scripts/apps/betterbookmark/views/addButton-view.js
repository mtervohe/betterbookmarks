/*
*
* */
define('apps/betterbookmark/views/addButton-view',
    function(){
        return jive.oo.Class.extend(function(protect) {

            this.render = function(parameters){
                var appDiv = document.getElementById('addfolder');
                appDiv.innerHTML = '<input id="folder-name" /> <button id="add-folder-button">Add Folder</button>';
            };

        });
    });