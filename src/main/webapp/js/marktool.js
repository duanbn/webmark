(function() {
    alert(document.title);
    alert(document.location.href);
    var metaTags = document.getElementsByTagName("meta");
    for (var i in metaTags) {
        var tag = metaTags[i];
        var tagName = tag.name.toLowerCase();
        if (tagName == 'keywords' || tagName == 'description') {
            alert(tag.content);
        }
    }
    /*
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.open("GET", document.location.href, false);
    xmlHttp.send();
    var pageHtml = xmlHttp.responseText;
    */
})(document);
