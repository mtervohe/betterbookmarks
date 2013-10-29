//Calling define with module ID, dependency array, and factory function
/**
 * @depends path=/plugins/betterbookmarks/resources/scripts/apps/betterbookmark/models/folder.js
 * @depends path=/plugins/betterbookmarks/resources/scripts/apps/betterbookmark/controllers/listController.js
 */
define('apps/betterbookmark/main',['apps/betterbookmark/models/folder','apps/betterbookmark/controllers/listController', 'jquery'], function(Folder, listController, $) {

    console.log("MAIN CALLED");

    var folders = [new Folder('Matt'),
               new Folder('Alex'),
               new Folder('Kat'),
               new Folder('Rory')];

    for (var i = 0, len = folders.length; i < len; i++){
        console.log(folders[i].name);
    }

    localStorage.folders = JSON.stringify(folders);

    /**/
    $(document).ready(function(){
        listController.start();
    });

    /*
    return jive.oo.Class.extend(function(protect) {
    });
    */
});



    /*
    $j(document).ready(function(){
        $j("#button1").click(function(){
            $j("#test").hide();
        });
    });
    * */
