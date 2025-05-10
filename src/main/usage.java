package main;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;

@Retention(RUNTIME)
public @interface usage {
	String usager() default "null";
	int classnumber() default 0;

}
