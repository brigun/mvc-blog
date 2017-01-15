$(function(){
	$('#messages li').click(function(){
		$(this).fadeOut();
	});
	setTimeOut(function(){
		$('#messages li.info').fadeOut();
	},3000);
	
		});