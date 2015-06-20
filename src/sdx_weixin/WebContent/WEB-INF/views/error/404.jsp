<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>404</title>
<link rel="stylesheet" type="text/css" href="css/errorstyles.css" />

</head>
<body>
<div id="rocket"></div>
<hgroup>
    <h1>亲，您要飞得更高。。。。。。</h1>
    <h2>但是，亲，您与地球已经失去联系了。。。。。。</h2>
</hgroup>

<p class="createdBy"><a href="/auth_center">我要回地球</a></p>
<script src="js/jquery.js"></script>
<script type="text/javascript">
$(window).load(function(){
    function animSteam(){
		$('<span></span>').addClass('steam'+Math.floor(Math.random()*2 + 1)).
			//css("marginLeft", -10 + Math.floor(Math.random()*20)).
			appendTo('#rocket').animate({
            left:'-=58',
            bottom:'-=100'
        }, 120,function(){
            $(this).remove();
            setTimeout(animSteam,10);
        });
    }

    function moveRocket(){
        $('#rocket').animate({'left':'+=100'},5000).delay(1000)
                    .animate({'left':'-=100'},5000,function(){
                setTimeout(moveRocket,1000);
        });
    }

    moveRocket();
    animSteam();
});

</script>
</body>
</html>
