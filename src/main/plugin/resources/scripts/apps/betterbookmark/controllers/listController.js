/**
 * @depends path=/plugins/betterbookmarks/resources/scripts/apps/betterbookmark/views/listView.js
 */
define('apps/betterbookmark/controllers/listController',['apps/betterbookmark/views/listView'], function(listView){

    console.log("listController CALLED");
    function start(){
        var folders = JSON.parse(localStorage.folders);
        listView.render({folders:folders});
    }

    return {
        start:start
    };
});