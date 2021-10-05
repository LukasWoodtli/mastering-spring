package com.woodtli.spring_boot.bean;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/* Some Bean Validation annotations:
@AssertFalse, @AssertTrue: For Boolean elements. Checks the annotated
element.
@AssertFalse: Checks for false. @Assert checks for true.
@Future: The annotated element must be a date in the future.
@Past: The annotated element must be a date in the past.
@Max: The annotated element must be a number whose value must be lower or
equal to the specified maximum.
@Min: The annotated element must be a number whose value must be higher or
equal to the specified minimum.
@NotNull: The annotated element cannot be null.
@Pattern: The annotated {@code CharSequence} element must match the
specified regular expression. The regular expression follows the Java regular
expression conventions.
@Size: The annotated element size must be within the specified boundaries. */

public class Todo {
	private int id;
	@NotNull
	private String user;
	@Size(min = 9, message = "Enter at least 10 Characters.")
	private String desc;
	private Date targetDate;
	private boolean isDone;

	public Todo() {
	}

	public Todo(int id, String user, String desc, Date targetDate, boolean isDone) {
		super();
		this.id = id;
		this.user = user;
		this.desc = desc;
		this.targetDate = targetDate;
		this.isDone = isDone;
	}

	public int getId() {
		return id;
	}

	public String getUser() {
		return user;
	}

	public String getDesc() {
		return desc;
	}

	public Date getTargetDate() {
		return targetDate;
	}

	public boolean isDone() {
		return isDone;
	}

}