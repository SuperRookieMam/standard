package com.standard.codecreate.feature.replace;

public class TableTab {
    public static String templateBgin="<template>" +System.getProperty("line.separator")+ "   <div>"+System.getProperty("line.separator") ;
    public static String templateEnd="   </div>"+System.getProperty("line.separator") + "</template>"+System.getProperty("line.separator") ;
    public static String serchFormBegin ="     <el-form ref=\"serchObj\"" +System.getProperty("line.separator")+
            "              class=\"demo-form-inline\"" +System.getProperty("line.separator")+
            "              size=\"mini\"" +System.getProperty("line.separator")+
            "              label-width=\"80px\">"+System.getProperty("line.separator");
    public static String serchFormEnd ="</el-form>"+System.getProperty("line.separator");
    public static String elRowBegin ="         <el-row>"+System.getProperty("line.separator");
    public static String elRowEnd ="        </el-row>"+System.getProperty("line.separator");
    public static String elColBegin = "            <el-col :span=\"4\">"+System.getProperty("line.separator");
    public static String elColEnd = "            </el-col>"+System.getProperty("line.separator");
    public static String elFormItemBegin = "                  <el-form-item label=\"活动名称\" prop=\"name\">"+System.getProperty("line.separator");
    public static String elFormItemEnd = "              </el-form-item>"+System.getProperty("line.separator");
    public static String elTableInput = "                      <el-input v-model=\"FormData.name\"/>"+System.getProperty("line.separator");
    public static String elTableDatePicker = "                 <el-date-picker v-model=\"serchObj['time']\""+System.getProperty("line.separator") +
            "                                 type=\"datetime\"" +System.getProperty("line.separator")+
            "                                 placeholder=\"选择日期时间\">"+System.getProperty("line.separator");
    public static String elTableSelcectBegin = "                    <el-select v-model=\"form.region\" placeholder=\"请选择活动区域\">"+System.getProperty("line.separator");
    public static String elTableSelcectEnd = "                    </el-select>"+System.getProperty("line.separator");
    public static String elTableOption = "                      <el-option v-for=\"(item,index) in selectData\""+System.getProperty("line.separator") +
            "                                               :key=\"index\"" +System.getProperty("line.separator")+
            "                                               :label=\"item.lable\"" +System.getProperty("line.separator")+
            "                                               :value=\"item.value\"/>"+System.getProperty("line.separator");
    public static String elTableButtonBegin = "              <el-button type=\"primary\""+System.getProperty("line.separator") +
            "                         size=\"mini\"" +System.getProperty("line.separator")+
            "                         @click=\"filterByserchObj\">"+System.getProperty("line.separator");
    public static String elTableButtonEnd = "                          </el-button>"+System.getProperty("line.separator");
    public static String elTableBegin = "      <el-table :data=\"tableData\"" +System.getProperty("line.separator")+
            "                style=\"width: 100%\">"+System.getProperty("line.separator");

    public static String elTableEnd = "      </el-table>"+System.getProperty("line.separator");
    public static String elTableColumn = "         <el-table-column" +System.getProperty("line.separator")+
            "                 label=\"商品 ID\"" +System.getProperty("line.separator")+
            "                 prop=\"id\"/>"+System.getProperty("line.separator");
    public static String elTableColumnLast = "        <el-table-column label=\"操作\" :min-width=\"60\">"+System.getProperty("line.separator") +
            "                 <template slot-scope=\"scope\">"+System.getProperty("line.separator") +
            "                   <el-button type=\"text\" size=\"mini\" @click=\"edit('testForm',scope.row)\">编辑</el-button>"+System.getProperty("line.separator") +
            "                   <el-button type=\"text\" size=\"mini\" @click=\"deleteRow(controllerMapping,scope.row)\">编辑</el-button>"+System.getProperty("line.separator") +
            "                 </template>"+System.getProperty("line.separator") +
            "         </el-table-column>"+System.getProperty("line.separator");
    public static String elPagination =" <el-pagination" +System.getProperty("line.separator")+
            "               @size-change=\"handleSizeChange\""+System.getProperty("line.separator") +
            "               @current-change=\"handleCurrentChange\""+System.getProperty("line.separator") +
            "               :current-page=\"params.pageNum\""+System.getProperty("line.separator") +
            "               :page-sizes=\"pageSizes\"" +System.getProperty("line.separator")+
            "               :page-size=\"params.pageSize\"" +System.getProperty("line.separator")+
            "               layout=\"total, sizes, prev, pager, next, jumper\"" +System.getProperty("line.separator")+
            "               :total=\"totalPage\"/>"+System.getProperty("line.separator");
    public static  String scriptBegin ="<script>"+System.getProperty("line.separator");
    public static  String scriptEnd ="</script>"+System.getProperty("line.separator");
    public static  String import1 ="   import { Component, Mixins } from 'vue-property-decorator'"+System.getProperty("line.separator");
    public static  String import2 ="   import TableBase from '../../../plugins/TableBase'"+System.getProperty("line.separator");

    public static  String classStr ="" +System.getProperty("line.separator")+
            "  @Component" +System.getProperty("line.separator")+
            "  export default class TestTalbles extends Mixins(TableBase)  {"+System.getProperty("line.separator");
    public static String  lstStr ="serchObj = {}"+System.getProperty("line.separator") +
            "" +System.getProperty("line.separator")+
            "    params = {"+System.getProperty("line.separator") +
            "        pageSize: 50," +System.getProperty("line.separator")+
            "        pageNum: 1"+System.getProperty("line.separator") +
            "    }" +System.getProperty("line.separator")+
            "    pageSizes = [10, 20, 40, 80]"+System.getProperty("line.separator") +
            "" +
            "    tableData = []"+System.getProperty("line.separator") +
            "" +
            "    controllerMapping = ''"+System.getProperty("line.separator") +
            "" +
            "    handleSizeChange (val) {"+System.getProperty("line.separator") +
            "     this.params.  = val" +System.getProperty("line.separator")+
            "      this.filterByserchObj()" +System.getProperty("line.separator")+
            "    }" +
            "    handleCurrentChange (val) {" +System.getProperty("line.separator")+
            "      this.params.pageNum = val" +System.getProperty("line.separator")+
            "      this.filterByserchObj()"+System.getProperty("line.separator") +
            "    }" +System.getProperty("line.separator")+
            "    filterByserchObj () {" +System.getProperty("line.separator")+
            "          this.search(this.templateSearch, this.serchObj, this.params, 'menuFunction').then(ele => {"+System.getProperty("line.separator") +
            "            if (ele.code === 0) {" +System.getProperty("line.separator")+
            "              this.tableData = ele.data.list" +System.getProperty("line.separator")+
            "              this.totalPage = ele.data.total" +System.getProperty("line.separator")+
            "            }" +System.getProperty("line.separator")+
            "          })" +System.getProperty("line.separator")+
            "        }" +System.getProperty("line.separator")+
            "    created () {" +System.getProperty("line.separator")+
            "      this.this.filterByserchObj()"+System.getProperty("line.separator") +
            "    }" +
            " }";



}
