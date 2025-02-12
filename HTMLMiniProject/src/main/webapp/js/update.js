let bCheck=true
$(function(){
	$('.ups').hide()
	$('.update').click(function(){
		let rno=$(this).attr('data-rno') //span 여러개중에 내가 클릭한 값 
		$('.ups').hide();
		if(bCheck==true)
		{
			$('#m'+rno).show();
			$(this).text("취소");
			bCheck=false;
		}
		else
		{
			$('#m'+rno).hide();
			$(this).text("수정");
			bCheck=true;			
		}
	})
})