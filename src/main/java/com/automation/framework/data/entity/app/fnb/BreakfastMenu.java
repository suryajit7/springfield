package com.automation.framework.data.entity.app.fnb;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;
import org.simpleframework.xml.Element;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Jacksonized
public class BreakfastMenu {

    @Element
    private Food food;





}
