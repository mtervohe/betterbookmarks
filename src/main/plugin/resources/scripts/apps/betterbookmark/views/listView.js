/**
 * Created with IntelliJ IDEA.
 * User: matthew.hernandez
 * Date: 10/22/13
 * Time: 9:20 PM
 * To change this template use File | Settings | File Templates.
 */
define('apps/betterbookmark/views/listView',function(){
    console.log("listView CALLED");
    function render(parameters){
        var appDiv = document.getElementById('test');

        var folders = parameters.folders;

        var html='<ul>';
        for (var i = 0, len = folders.length; i < len; i++){
            html+='<li>'+folders[i].name+'</li>';
        }
        html+='</ul>';
        console.log(html);
        appDiv.innerHTML=html;
    }

    return {
        render:render
    };
});