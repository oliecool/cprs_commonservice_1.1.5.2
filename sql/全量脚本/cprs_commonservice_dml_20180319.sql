/*==============================================================*/            
/* Author    : 何荣                                              */
/* Date      : 2018-02-08                                      */
/* Notes     : 修改内容如下：                                   */
/* 注:不可重复执行
 */
/*==============================================================*/
use cprs_commonservice; 
insert into sms_model (ID, SMS_CODE, SMS_TYPE, SMS_CONTENT, STATUS, GMT_CREATE, GMT_MODIFY)
values (1, '100700', '预约入库成功', '您的托管商品#goodsName#预约入库已经受理，您的预约号：#applyNo#，请在#applyDate#当天到交易中心营业大厅进行登记入库，为避免无法办理的情形，请提前缴存足额的鉴评费：#apfRate#元，托管上市费：#listingRate#元至交易账户中。', 'using',now(), now());

insert into sms_model (ID, SMS_CODE, SMS_TYPE, SMS_CONTENT, STATUS, GMT_CREATE, GMT_MODIFY)
values (2, '100113', '提示买方已开发票', '尊敬的客户，您的电子交易合同号：#orderNo#,发票对方已开具，请及时查收并登录系统予以确认，祝您交易顺利！', 'using', now(), now());

insert into sms_model (ID, SMS_CODE, SMS_TYPE, SMS_CONTENT, STATUS, GMT_CREATE, GMT_MODIFY)
values (3, '100106', '短信提醒买方提货日期', '尊敬的客户，您的提货申请已受理。提货单号：#pickupNo#，提货日期：#year#年#month#月#day#日，请妥善保管好您的提货码：#deliveryPassword#。', 'using',now(), now());

insert into sms_model (ID, SMS_CODE, SMS_TYPE, SMS_CONTENT, STATUS, GMT_CREATE, GMT_MODIFY)
values (4, '100100', '用户开户审批通过结果', '您的开户注册已成功，您的交易账号为(#mchtAccount#)，请及时完成银商绑定签约，并妥善保管好您的交易账号及密码。', 'using', now(), now());

insert into sms_model (ID, SMS_CODE, SMS_TYPE, SMS_CONTENT, STATUS, GMT_CREATE, GMT_MODIFY)
values (5, '100101', '线上开户手机验证码', '您的开户手机验证码为：#identifyingCode#。验证码30分钟有效只能输入1次，若30分钟内未输入，需重新获取。验证码转发无效。', 'using',now(), now());

insert into sms_model (ID, SMS_CODE, SMS_TYPE, SMS_CONTENT, STATUS, GMT_CREATE, GMT_MODIFY)
values (6, '100109', '重置密码，验证码', '尊敬的客户，您的密码将被重置，验证码为#code#。请您妥善保管验证码，并及时登录交易系统重置密码。', 'using',now(), now());

insert into sms_model (ID, SMS_CODE, SMS_TYPE, SMS_CONTENT, STATUS, GMT_CREATE, GMT_MODIFY)
values (7, '100802', '资金出金审核拒绝短信', '尊敬的客户，您在#date#资金帐号为#fundAccount#，出金金额为#amount#的出金申请被拒绝', 'using', now(), now());

insert into sms_model (ID, SMS_CODE, SMS_TYPE, SMS_CONTENT, STATUS, GMT_CREATE, GMT_MODIFY)
values (8, '100803', '资金出金审核成功短信', '尊敬的客户，您在#date#资金帐号为#fundAccount#的出金申请,出金金额#amount#，已经通过申请并且出金成功', 'using',now(), now());

insert into sms_model (ID, SMS_CODE, SMS_TYPE, SMS_CONTENT, STATUS, GMT_CREATE, GMT_MODIFY)
values (9, '100800', '资金出金提醒短信', '尊敬的客户，您在#date#从资金帐号为#fundAccount#转出资金:#amount#元', 'using',now(), now());

insert into sms_model (ID, SMS_CODE, SMS_TYPE, SMS_CONTENT, STATUS, GMT_CREATE, GMT_MODIFY)
values (10, '100801', '资金出金申请提醒短信', '尊敬的客户，您在#date#为资金帐号#fundAccount#提交了出金申请，出金金额为#amount#元，申请人#applyMan#', 'using',now(), now());

insert into sms_model (ID, SMS_CODE, SMS_TYPE, SMS_CONTENT, STATUS, GMT_CREATE, GMT_MODIFY)
values (11, '100804', '资金出金审核错误短信', '尊敬的客户，您在#date#资金帐号为#fundAccount#,出金金额#amount#的出金申请,审核人员在审核时出现系统错误', 'using', now(), now());

insert into sms_model (ID, SMS_CODE, SMS_TYPE, SMS_CONTENT, STATUS, GMT_CREATE, GMT_MODIFY)
values (12, '100103', '开户审批不通过结果', '尊敬的客户，您在本交易中心的开户注册未成功，请您登陆交易中心官网（http://www.hundsun.com）查询审核结果，给您带来不便深感抱歉。祝您愉快！', 'using', now(), now());

insert into sms_model (ID, SMS_CODE, SMS_TYPE, SMS_CONTENT, STATUS, GMT_CREATE, GMT_MODIFY)
values (13, '100901', '交易商修改手机验证码', '尊敬的交易商，您在东北商品交易中心的修改手机号验证码为：#code#。该验证码30分钟有效且只能输入1次，若30分钟内未输入，需重新获取。验证码转发无效。', 'using', now(), now());

insert into sms_model (ID, SMS_CODE, SMS_TYPE, SMS_CONTENT, STATUS, GMT_CREATE, GMT_MODIFY)
values (14, '100900', '交易商转户申请验证码', '尊敬的交易商，您在东北商品交易中心的交易商转户申请验证码为：#code#。该验证码30分钟有效且只能输入1次，若30分钟内未输入，需重新获取。验证码转发无效。', 'using', now(), now());

insert into sms_model (ID, SMS_CODE, SMS_TYPE, SMS_CONTENT, STATUS, GMT_CREATE, GMT_MODIFY)
values (15, '100131', '提货申请审核通过', '尊敬的客户，您在本中心的商品预约提货审核已通过，提货单号为#batchNo#，商品名称为#stockName#，商品代码为#stockCode#，预约提货数量为#diliveryAmount#，预约提货日期为#confirmDate#，请到交易中心指定的地点提货。', 'using', now(), now());

insert into sms_model (ID, SMS_CODE, SMS_TYPE, SMS_CONTENT, STATUS, GMT_CREATE, GMT_MODIFY)
values (16, '100132', '提货申请审核不通过', '尊敬的客户，您在本中心的商品预约提货审核未通过，提货单号为#batchNo#，商品名称为#stockName#，商品代码为#stockCode#，预约提货数量为#diliveryAmount#，预约提货日期为#confirmDate#。', 'using',now(), now());

insert into sms_model (ID, SMS_CODE, SMS_TYPE, SMS_CONTENT, STATUS, GMT_CREATE, GMT_MODIFY)
values (17, '100133', '发货确认', '尊敬的客户，您在本中心预约的商品已经发货，商品名称为#stockName#，商品代码为#stockCode#，商品数量为#diliveryAmount#，提货单号为#batchNo#，请注意查收，详情咨询客服热线4000388386。', 'using',now(), now());

insert into sms_model (ID, SMS_CODE, SMS_TYPE, SMS_CONTENT, STATUS, GMT_CREATE, GMT_MODIFY)
values (18, '100200', '货权过户审核通过', '尊敬的交易商，您在#company#有一个货权过户单，商品名称：#stockName#，单价：#stockPrice#，数量：#stockAmount#，领取时间：#gmtCreate#，失效时间：#gmtInvalid#，请注意及时签收。', 'using', now(), now());

insert into sms_model (ID, SMS_CODE, SMS_TYPE, SMS_CONTENT, STATUS, GMT_CREATE, GMT_MODIFY)
values (20, '200100', '招商审核通过', '尊敬的客户您好！您发布的关于版权《#fullName#》的招商信息已于#applyDate#当天通过审核，您的招商编号：#attractInvestmentNo#，请尽快登陆平台发布上线。', 'using',now(), now());

insert into sms_model (ID, SMS_CODE, SMS_TYPE, SMS_CONTENT, STATUS, GMT_CREATE, GMT_MODIFY)
values (21, '200101', '招商审核驳回', '尊敬的客户您好！您发布的关于版权《#fullName#》的招商信息已于#applyDate#当天经过审核，审核结果为：驳回，理由：#reason#，您的招商编号：#attractInvestmentNo#，请尽快登陆平台修改招商信息。', 'using', now(), now());

insert into sms_model (ID, SMS_CODE, SMS_TYPE, SMS_CONTENT, STATUS, GMT_CREATE, GMT_MODIFY)
values (22, '200102', '版权操作成功', '尊敬的客户您好！您的版权《#fullName#》已于#applyDate#当天#operate#成功，您的版权编号：#copyrightCode#，请及时登录平台确认版权信息，如有疑问请联系客服', 'using', now(), now());

insert into sms_model (ID, SMS_CODE, SMS_TYPE, SMS_CONTENT, STATUS, GMT_CREATE, GMT_MODIFY)
values (23, '200103', '发起申请合作', '尊敬的客户您好！您于#date#当天收到一条申请合作，被申请的版权《#fullName#》，申请编号：#applyNo#，请尽快登录平台审核。', 'using',now(), now());

insert into sms_model (ID, SMS_CODE, SMS_TYPE, SMS_CONTENT, STATUS, GMT_CREATE, GMT_MODIFY)
values (24, '200104', '发起申请合作', '尊敬的客户您好！您于#date#当天发起合作申请成功并由平台冻结保证金#amount#元，申请的版权《#fullName#》，申请编号：#applyNo#', 'using',now(), now());

insert into sms_model (ID, SMS_CODE, SMS_TYPE, SMS_CONTENT, STATUS, GMT_CREATE, GMT_MODIFY)
values (25, '200105', '通过申请合作', '尊敬的客户您好！您发起的申请合作于#date#当天已通过审核，申请的版权《#fullName#》，申请编号：#applyNo#。', 'using',now(), now());

insert into sms_model (ID, SMS_CODE, SMS_TYPE, SMS_CONTENT, STATUS, GMT_CREATE, GMT_MODIFY)
values (26, '200106', '驳回申请合作', '尊敬的客户您好！您发起的申请合作已于#date#当天被驳回，您冻结的保证金#amount#元已解除冻结，驳回理由为:#reason#，申请的版权《#fullName#》，申请编号：#applyNo#。', 'using',now(), now());

insert into sms_model (ID, SMS_CODE, SMS_TYPE, SMS_CONTENT, STATUS, GMT_CREATE, GMT_MODIFY)
values (27, '200107', '撤销申请合作', '尊敬的客户您好！您接收的申请合作已于#date#当天被商家撤回，被申请的版权《#fullName#》，申请编号：#applyNo#。', 'using',now(), now());

insert into sms_model (ID, SMS_CODE, SMS_TYPE, SMS_CONTENT, STATUS, GMT_CREATE, GMT_MODIFY)
values (28, '200108', '撤销申请合作', '尊敬的客户您好！由于您于#date#当天撤回了申请合作，您冻结的保证金#amount#元已解除冻结，申请的版权《#fullName#》，申请编号：#applyNo#', 'using',now(), now());

insert into sms_model (ID, SMS_CODE, SMS_TYPE, SMS_CONTENT, STATUS, GMT_CREATE, GMT_MODIFY)
values (29, '200109', '确认合同订单', '尊敬的客户您好！您有一条待确认的合同订单，订单编号：#orderNo#，日期#date#，请尽快登录平台确认。', 'using', now(), now());

insert into sms_model (ID, SMS_CODE, SMS_TYPE, SMS_CONTENT, STATUS, GMT_CREATE, GMT_MODIFY)
values (30, '200112', '合同订单完成', '尊敬的客户您好！您的一条合同订单已于#date#当天达成协议，成功转入授权金#amount#元，支付手续费#amount2#元，原冻结的保证金#amount3#元已解除冻结，订单编号：#orderNo#。', 'using', now(), now());

insert into sms_model (ID, SMS_CODE, SMS_TYPE, SMS_CONTENT, STATUS, GMT_CREATE, GMT_MODIFY)
values (31, '200113', '版权方确认合同订单', '尊敬的客户您好！您于#date#当天确认了合同订单并由平台冻结手续费#amount#元，保证金#amount2#元，订单编号：#orderNo#。', 'using', now(), now());

insert into sms_model (ID, SMS_CODE, SMS_TYPE, SMS_CONTENT, STATUS, GMT_CREATE, GMT_MODIFY)
values (32, '200114', '合同订单作废', '尊敬的客户您好！您的一条合同订单已于#date#当天作废，您冻结的保证金#amount#元已解除冻结，订单编号：#orderNo#。', 'using',now(), now());

insert into sms_model (ID, SMS_CODE, SMS_TYPE, SMS_CONTENT, STATUS, GMT_CREATE, GMT_MODIFY)
values (33, '200110', '商家退回合同订单', '尊敬的客户您好！您的一条合同订单于#date#当天已被商家退回，您冻结的手续费#amount#元以及保证金#amount2#元已解除冻结，订单编号：#orderNo#，原因：#reason#。', 'using', now(), now());

insert into sms_model (ID, SMS_CODE, SMS_TYPE, SMS_CONTENT, STATUS, GMT_CREATE, GMT_MODIFY)
values (34, '300101', '提示付款', '尊敬的客户您好！您的资金账户《#fundAccount#》在《#entrustTime#》时完成交易订单，预订单号为《#entrustPreNO#》，请在交易时限内近快完成付款。', 'using', now(), now());

insert into sms_model (ID, SMS_CODE, SMS_TYPE, SMS_CONTENT, STATUS, GMT_CREATE, GMT_MODIFY)
values (35, '200111', '合同订单完成', '尊敬的客户您好！您的一条合同订单已于#date#当天达成协议，成功支付手续费#amount#元，支付授权金#amount2#元，原冻结的保证金#amount3#元已解除冻结，订单编号：#orderNo#。', 'using',now(), now());

insert into sms_model (ID, SMS_CODE, SMS_TYPE, SMS_CONTENT, STATUS, GMT_CREATE, GMT_MODIFY)
values (36, '200115', '合同订单作废', '尊敬的客户您好！您的一条合同订单已于#date#当天作废，订单编号：#orderNo#。', 'using', now(), now());

insert into sms_model (ID, SMS_CODE, SMS_TYPE, SMS_CONTENT, STATUS, GMT_CREATE, GMT_MODIFY)
values (37, '200116', '合同订单强制作废', '尊敬的客户您好！您的一条合同订单已于#date#当天由平台强制作废,您冻结的保证金#amount#元已解除冻结,订单编号：#orderNo#,如有疑问请联系客服。', 'using', now(), now());

insert into sms_model (ID, SMS_CODE, SMS_TYPE, SMS_CONTENT, STATUS, GMT_CREATE, GMT_MODIFY)
values (38, '200117', '合同订单强制作废', '尊敬的客户您好！您的一条合同订单已于#date#当天由平台强制作废,订单编号：#orderNo#,如有疑问请联系客服。', 'using', now(), now());

insert into sms_model (ID, SMS_CODE, SMS_TYPE, SMS_CONTENT, STATUS, GMT_CREATE, GMT_MODIFY)
values (39, '200118', '合同订单强制作废', '尊敬的客户您好！您的一条合同订单已于#date#当天由平台强制作废,您冻结的保证金#amount#元已由平台扣除，订单编号：#orderNo#,如有疑问请联系客服。', 'using',now(), now());

insert into sms_model (ID, SMS_CODE, SMS_TYPE, SMS_CONTENT, STATUS, GMT_CREATE, GMT_MODIFY)
values (40, '200119', '合同订单强制作废', '尊敬的客户您好！您的一条合同订单已于#date#当天由平台强制作废,您冻结的手续费#amount#元已解除冻结,保证金#amount2#元已由平台扣除，订单编号：#orderNo#,如有疑问请联系客服。', 'using', now(), now());

insert into sms_model (ID, SMS_CODE, SMS_TYPE, SMS_CONTENT, STATUS, GMT_CREATE, GMT_MODIFY)
values (41, '400101', '角色申请审核通过', '尊敬的客户您好！您的账号[#userAccount#]申请的角色[#roleName#]当天经过审核，审核结果为：通过，请尽快登录平台查看。', 'using',now(), now());

insert into sms_model (ID, SMS_CODE, SMS_TYPE, SMS_CONTENT, STATUS, GMT_CREATE, GMT_MODIFY)
values (42, '400102', '角色申请审核不通过', '尊敬的客户您好！您的账号[#userAccount#]申请的角色[#roleName#]当天经过审核，审核结果为：驳回，理由：[#auditRemark#]。', 'using', now(), now());

insert into sms_model (ID, SMS_CODE, SMS_TYPE, SMS_CONTENT, STATUS, GMT_CREATE, GMT_MODIFY)
values (43, '400103', '三方用户绑定', '您的账户绑定手机验证码为：#code#。验证码30分钟有效只能输入1次，若30分钟内未输入，需重新获取。验证码转发无效。', 'using',now(), now());

insert into sms_model (ID, SMS_CODE, SMS_TYPE, SMS_CONTENT, STATUS, GMT_CREATE, GMT_MODIFY)
values (44, '365708', '后台管理员重置密码', '尊敬的用户：你的#pwdType#重置为#code#，请妥善保管好您的密码。', 'using',now(), now());

insert into sms_model (ID, SMS_CODE, SMS_TYPE, SMS_CONTENT, STATUS, GMT_CREATE, GMT_MODIFY)
values (45, '334323', '手机登录', '尊敬的客户，您在本商城的手机验证码为：#code#。该验证码30分钟有效且只能输入1次，若30分钟内未输入，需重新获取，转发无效。', 'using', now(), now());


commit; 
