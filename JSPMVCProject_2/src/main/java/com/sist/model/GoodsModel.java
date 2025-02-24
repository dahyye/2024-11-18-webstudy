package com.sist.model;
import com.sist.ann.*;

public class GoodsModel {
	@RequestMapping("goods/list.do")
	public void goodsListDate()
	{
		System.out.println("상품 목록 출력");
	}
	@RequestMapping("goods/detail.do")
	public void goodDetailDate()
	{
		System.out.println("상품 상세보기 출력");
	}
}
