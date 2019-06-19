package com.standard.codecreate.feature.replace;

public class FormTab {
    public static String templateBgin="<template>" +System.getProperty("line.separator")+ "   <div>"+System.getProperty("line.separator") ;
    public static String templateEnd="   </div>"+System.getProperty("line.separator") + "</template>"+System.getProperty("line.separator") ;
    public static String formBegin ="    <el-form :model=\"FormData\""+System.getProperty("line.separator") +
            "                 :rules=\"rules\"" +System.getProperty("line.separator")+
            "                 ref=\"FormData\"" +System.getProperty("line.separator")+
            "                 label-width=\"100px\"" +System.getProperty("line.separator")+
            "                 size=\"mini\">"+System.getProperty("line.separator");
    public static String serchFormEnd ="</el-form>"+System.getProperty("line.separator");
    public static String elTabsBegin ="      <el-tabs v-model=\"activeName\" @tab-click=\"handleClick\">"+System.getProperty("line.separator");
    public static String elTabsEnd ="      </el-tabs>"+System.getProperty("line.separator");
    public static String elTabPaneBegin ="         <el-tab-pane label=\"用户管理\" name=\"first\">"+System.getProperty("line.separator");
    public static String elTabPaneEnd ="         </el-tab-pane>"+System.getProperty("line.separator");
    public static String elRowBegin ="            <el-row>"+System.getProperty("line.separator");
    public static String elRowEnd ="            </el-row>"+System.getProperty("line.separator");
    public static String elColBegin ="               <el-col :span=\"12\">"+System.getProperty("line.separator");
    public static String elColEnd ="               </el-col>"+System.getProperty("line.separator");
    public static String elFormItemBegin ="                  <el-form-item label=\"活动名称\" prop=\"name\">"+System.getProperty("line.separator");
    public static String elFormItemEnd ="                  </el-form-item>"+System.getProperty("line.separator");
    public static String elInput ="                      <el-input v-model=\"FormData.name\"/>"+System.getProperty("line.separator");
    public static String router ="            <router-view/>"+System.getProperty("line.separator");
    public static String elButtonBegin ="              <el-button type=\"primary\" @click=\"submitForm('formData')\">"+System.getProperty("line.separator");
    public static String elButtonEnd="            </el-button>"+System.getProperty("line.separator");
    public static String scriptBegin ="<script>"+System.getProperty("line.separator");
    public static String scriptEnd="</script>"+System.getProperty("line.separator");
    public static String import1 ="  import { Component, Prop, Mixins } from 'vue-property-decorator'"+System.getProperty("line.separator");
    public static String import2="  import TableBase from '../../../plugins/TableBase'"+System.getProperty("line.separator");
    public static String ComponentBegin=" @Component({"+System.getProperty("line.separator");
    public static String ComponentEnd="  })"+System.getProperty("line.separator");
    public static String componentsBegin="    components: {"+System.getProperty("line.separator");
    public static String componentsEnd="    }"+System.getProperty("line.separator");
    public static String classBegin="  export default class TestForm extends Mixins(TableBase) {"+System.getProperty("line.separator");
    public static String classEnd=" }"+System.getProperty("line.separator");
    public static String classInner="@Prop({ default: () => 'new' })"+System.getProperty("line.separator") +
            "    id"+System.getProperty("line.separator") +
            "    activeName = 'base'" +System.getProperty("line.separator")+
            "    controllerMapping = 'menuFunction'" +System.getProperty("line.separator")+
            "    rules = {" +System.getProperty("line.separator")+
            "          name: ["+System.getProperty("line.separator") +
            "            {required: true, message: '请输入活动名称', trigger: 'blur'},"+System.getProperty("line.separator") +
            "            {min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur'}"+System.getProperty("line.separator") +
            "          ]" +System.getProperty("line.separator")+
            "        }" +System.getProperty("line.separator")+
            "    handleClick (tab, event) {" +System.getProperty("line.separator")+
            "       /* if (tab.name === 'formtest') {"+System.getProperty("line.separator") +
            "            this.$router.push({name: 'tt', params: {rowData: {id: 'new'}}})" +System.getProperty("line.separator")+
            "          } */" +System.getProperty("line.separator")+
            "    }" +System.getProperty("line.separator")+
            "    created () {"+System.getProperty("line.separator") +
            "     this.getFormData(this.controllerMapping, this.id)" +System.getProperty("line.separator")+
            "    }"+System.getProperty("line.separator");
}
