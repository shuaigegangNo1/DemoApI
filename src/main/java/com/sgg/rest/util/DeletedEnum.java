package com.sgg.rest.util;

public enum DeletedEnum {
		Y(1),N(0);
		private int value;
		private DeletedEnum(int value){
			this.value=value;
		}
		public int value(){
			return value;
		}
}
