package com.inacif.rekognition.web.app.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class MenuFunctionalityId implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "menu_id")
    private Long menuId;

    @Column(name = "functionality_id")
    private Long functionalityId;

}