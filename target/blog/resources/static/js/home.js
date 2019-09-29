function getArticle() {

    var optionJson = [];
    $.ajax({
        type: "GET",
        url: "/getArticle",
        data: {},
        datatype:"json",
        async: true,
        error: function (){
            window.alert("BBBBB")
        },
        success: function (data) {
            window.alert("AAAA")
            optionJson = data;
            var str = "<a href=\"#\"><h4>111"+optionJson.title+"</h4></a>\n" +
                "                    <div>\n" +
                "                        "+optionJson.articleArticle+"\n" +
                "                    </div>\n" +
                "                    <hr />";
            document.getElementById("article2").innerHTML = str;
        }
    });
}
