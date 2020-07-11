package com.huse.util;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class JsonResult {
    private String code;
    private String info;
}
