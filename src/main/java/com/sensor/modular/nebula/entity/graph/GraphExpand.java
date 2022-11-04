package com.sensor.modular.nebula.entity.graph;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description ="扩展查询入参实体")
public class GraphExpand {

    @Schema(description ="空间名称", example = "sense", required = true)
    private String space;

    @Schema(description ="边类型集合", required = true)
    private List<String> edgeList;

    @Schema(description ="方向: OUTFLOW(流出);INFLOW(流入);TWO-WAY(双向);", example = "TWO-WAY",required = true)
    private String direction;

    @Schema(description ="步数开始(单步/范围的开始)", example = "1", required = true)
    private Integer stepStart;

    @Schema(description ="步数结束(范围的结束;可无)", example = "2")
    @Min(1)
    private Integer stepEnd;

    @Schema(description ="结果条数", example = "100", required = true)
    @Max(Integer.MAX_VALUE)
    @Min(1)
    private Integer resultSize = Integer.MAX_VALUE;

    @Schema(description ="扩展点id集合", required = true)
    private List<Object> vidList;


    public String getStepEndResult() {
        if (stepEnd != null) {
            return ".." + stepEnd;
        }
        return "";
    }

    public String getVidList(String vidType) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < vidList.size(); i++) {
            Object vid = vidList.get(i);

            if (vidType.contains("STRING")) {
                stringBuffer.append("\"").append(vid).append("\"");
            }else {
                stringBuffer.append(vid);
            }
            if (vidList.size() > 1 && (i + 1) != vidList.size()) {
                stringBuffer.append(",");
            }
        }
        return stringBuffer.toString();
    }
}
