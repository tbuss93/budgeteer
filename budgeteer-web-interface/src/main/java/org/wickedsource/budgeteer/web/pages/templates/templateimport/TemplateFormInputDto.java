package org.wickedsource.budgeteer.web.pages.templates.templateimport;

import java.io.Serializable;

import lombok.Data;

@Data
public class TemplateFormInputDto implements Serializable {
	private long projectId;
	private String name;
	private String description;

	public TemplateFormInputDto(long projectId) {
		this.projectId = projectId;
	}
}
