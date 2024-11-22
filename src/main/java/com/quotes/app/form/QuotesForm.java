package com.quotes.app.form;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuotesForm {
	
	@Size(min = 1, max = 100, message = "名言は{min}～{max}文字以内で入力してください")
	private String quotes ;
	@Min(value=1,message="カテゴリを選択してください")
	private int category;
	@NotBlank(message = "発言者を入力してください")
	private String whose;
	
	private String whose_detail;
	
	private boolean mine;
}
