(function() {
    var title = document.title;
    var url = document.location.href;
    var keywords = "";
    var desc = "";

    var metaTags = document.getElementsByTagName("meta");
    for (var i in metaTags) {
        var tag = metaTags[i];
        if (tag.name && tag.name != "") {
            var tagName = tag.name.toLowerCase();
            if (tagName == 'keywords') {
                keywords = tag.content;
            }
            if (tagName == 'description') {
                desc = tag.content;
            }
        }
    }

    var height = 368;
    var width = 614;
    var iTop = (window.screen.availHeight - 30 - height)/2;
    var iLeft = (window.screen.availWidth - 10 - width)/2;
    var url = "http://localhost:8080/mark/showdlg.do?title=" + document.title + "&url=" + document.location.href + "&keyword=" + keywords + "&desc=" + desc;
    var args = "height=" + height + ", width=" + width + ", toolbar=no, menubar=no, top=" + iTop + ", left=" + iLeft + ", scrollbars=no, resizable=no, location=no, status=no";
    window.open(url, "test", args);

    /*
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.open("GET", document.location.href, false);
    xmlHttp.send();
    var pageHtml = xmlHttp.responseText;
    */
})(document);
