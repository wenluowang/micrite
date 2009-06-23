<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<script type="text/javascript">
/**
 * micrite.security.userDetail 用户信息，增加新用户
 * 
 * @author xingzhaoyong
 */
Ext.ns('micrite.security.userDetail');

//  FormPanel构造函数
micrite.security.userDetail.FormPanel = function() {
    //  role多选框数据源
    var dataStore = new Ext.data.Store({
        autoLoad:true,
        proxy: new Ext.data.HttpProxy({url: '/' + document.location.href.split("/")[3] + '/findRolesAll.action'}),    
        reader: new Ext.data.JsonReader({id: "id"}, [{name: 'id'},{name: 'name'}])
    });

    //  这里定义Panel的外观，内部控件等
    micrite.security.userDetail.FormPanel.superclass.constructor.call(this, {
        id: 'userDetailForm',
        bodyBorder: false,
        frame: true,
        style:'padding:1px',
        items: [{
            xtype: 'fieldset',
            labelWidth: 150,
            title: this.userDetailText,
            layout: 'form',
            collapsible: true,
            defaults: {width: 210},    
            defaultType: 'textfield',
            autoHeight: true,
            items: [{
                fieldLabel: this.fullnameText,
                name: 'user.fullname',
                allowBlank:false
            },{
                fieldLabel: this.emailaddressText,
                name: 'user.emailaddress',
                vtype: 'email'
            },{
                fieldLabel: this.loginnameText,
                name: 'user.loginname',
                allowBlank:false
            },{
                fieldLabel: this.passwordText,
                name: 'user.plainpassword',
                inputType: 'password',
                allowBlank:false
            },new Ext.ux.form.CheckboxField({
                fieldLabel: this.rolesText,
                name:'userRoles',
                hideOnSelect:false,
                store:dataStore,
                triggerAction:'all',
                valueField:'id',
                displayField:'name',
                emptyText:this.userRolesText,
                mode:'local',
                allowBlank:false
            })
            ]
        },{
        	buttonAlign:'center',
            buttons: [{
                text: mbLocale.submitButton,
		        scope:this,
		        formBind:true,
                handler: function() {
    	            // 构建form的提交参数
    	            var params = { 'userRoleIdsStr': this.getForm().findField('userRoles').getValue() };      
    	            // form提交
    	            this.getForm().submit({
    	                url: '/' + document.location.href.split("/")[3] + '/addUser.action',
    	                method: 'POST',
    	                disabled:true,
    	                waitMsg: mbLocale.waitingMsg,
    	                params:params,
    	                success: function(form, action) {
    	                    obj = Ext.util.JSON.decode(action.response.responseText);
    	                    showMsg('success', obj.message);	                
    	                },
    	                failure: function(form, action) {
    	                    obj = Ext.util.JSON.decode(action.response.responseText);
    	                    showMsg('failure', obj.message);
    	                }
    	            });
    	        }                    
            },{
                text: mbLocale.closeButton
            }]
        }]
    });
};// FormPanel构造函数结束

//  页面上的字符串在这里定义
micrite.security.userDetail.FormPanel = Ext.extend(micrite.security.userDetail.FormPanel, Ext.FormPanel, {
    userDetailText: 'User Detail',
    fullnameText: 'Full Name',
    emailaddressText: 'Email Address',
    loginnameText: 'User Name',
    passwordText: 'Password',
    rolesText: 'Role',
    userRolesText: 'Select Roles'
});

try{ userDetailLocale(); } catch(e){}
try {baseLocale();} catch (e) {}

Ext.onReady(function() {
    Ext.QuickTips.init();
    Ext.form.Field.prototype.msgTarget = 'side';
    
    var formPanel = new micrite.security.userDetail.FormPanel();
    
    if (mainPanel) {
        mainPanel.getActiveTab().add(formPanel);
        mainPanel.getActiveTab().doLayout();
    } else {
        new Ext.Viewport({layout:'fit',items:[formPanel]});
    }
});
</script>