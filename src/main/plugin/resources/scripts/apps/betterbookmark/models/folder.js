/**
 * Created with IntelliJ IDEA.
 * User: matthew.hernandez
 * Date: 10/22/13
 * Time: 8:54 PM
 * To change this template use File | Settings | File Templates.
 */
define('apps/betterbookmark/models/folder',function(){
    console.log("FOLDER CALLED");

    function Folder(name){
        this.name = name || 'Default name';
    }

    return Folder;
});