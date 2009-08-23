package org.gaixie.micrite.action;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.gaixie.micrite.common.search.SearchBean;
import org.gaixie.micrite.common.search.SearchFactory;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.chart.servlet.ServletUtilities;

import com.opensymphony.xwork2.ActionSupport;

public class GenericAction extends ActionSupport {
    private static final long serialVersionUID = -6554298643237377735L;

    //以Map格式存放操作的结果，然后由struts2-json插件转换为json对象
    private Map<String,Object> resultMap = new HashMap<String,Object>();

    //  起始索引
    private int start;
    //  限制数
    private int limit;
    //  记录总数（分页中改变页码时，会传递该参数过来）
    private int totalCount;

    private int chartWidth = 600;
    
    private int chartHeight = 450;
    
    private SearchBean[] queryBean;
    
    public boolean isFirstSearch(){
        return totalCount == 0;
    }
    
    @SuppressWarnings("unchecked")
    public void putResultList(List data){
        resultMap.put("totalCount", totalCount);
        resultMap.put("success", true);
        resultMap.put("data", data);
    }
    public void putChartResultList(JFreeChart chart){
        StandardEntityCollection entityCollection = new StandardEntityCollection();
        ChartRenderingInfo info = new ChartRenderingInfo(entityCollection);
        String filename = "";
        try {
            filename = ServletUtilities.saveChartAsPNG(chart, getChartWidth(), getChartHeight(), info, null);
            String mapName = "map"+new Date();
            String mapInfo = ChartUtilities.getImageMap(mapName, info);
            resultMap.put("success", true);
            resultMap.put("filename", filename);
            resultMap.put("map", mapInfo);
            resultMap.put("mapName", mapName);
        } catch (IOException e) {
            resultMap.put("success", false);
        }
    }
    public Map<String, Object> getResultMap() {
        return resultMap;
    }

    public void setResultMap(Map<String, Object> resultMap) {
        this.resultMap = resultMap;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public SearchBean[] getQueryBean() {
        return queryBean;
    }

    public void setQueryString(String queryString){
        this.queryBean = SearchFactory.getSearchTeam(queryString);
    }
    public int getChartWidth() {
        return chartWidth;
    }

    public void setChartWidth(int chartWidth) {
        this.chartWidth = chartWidth;
    }

    public int getChartHeight() {
        return chartHeight;
    }

    public void setChartHeight(int chartHeight) {
        this.chartHeight = chartHeight;
    }
}
