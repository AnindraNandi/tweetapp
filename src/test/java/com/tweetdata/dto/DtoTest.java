package com.tweetdata.dto;



import org.junit.jupiter.api.Test;

import com.openpojo.validation.Validator;
import com.openpojo.validation.ValidatorBuilder;
import com.openpojo.validation.rule.impl.GetterMustExistRule;
import com.openpojo.validation.rule.impl.SetterMustExistRule;
import com.openpojo.validation.test.impl.GetterTester;
import com.openpojo.validation.test.impl.SetterTester;

public class DtoTest {
	private String beanPackageName = "com.tweetdata.bean";
	  @Test
	   void validateBean() {
	    Validator validator = ValidatorBuilder.create()
	                            .with(new SetterMustExistRule(),
	                                  new GetterMustExistRule())
	                            .with(new SetterTester(),
	                                  new GetterTester())
	                          
	                            .build();
    validator.validate(beanPackageName);
}
}
