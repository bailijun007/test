package com.blj.pojo.ehr;
//1×× 保留
//2×× 表示请求成功地接收
//3×× 为完成请求客户需进一步细化请求
//4×× 客户错误
//5×× 服务器错误

/**
 * 异常信息的描述
 * code: 表示异常的错误码
 * message：表示对应异常code的详情描述
 *   001
 * 1、错误码暂定都是6位数字(通用错误码除外)
 * 2、错误码按模块按功能场景分级分段，
 *    a.前三位错误码表示模块：（第一位表示具体的分类，后面两位区该类下的具体模块）
 *      第一位表示具体的分类
 *      --1×× 保留（路由等异常码）
 *      --2×× 表示请求成功地接收
 *      --3×× 为完成请求客户需进一步细化请求
 *      --4×× 客户端的请求返回码——调用方法的参数校验失败（如：参数不为空等）
 *      --5×× 客户端的请求返回码——后台业务逻辑校验失败（如：没有权限，获取文件失败，）
 *      --6xx 第三方接口返回码
 *
 *
 *    b.后三位表示模块下具体的异常错误
 *
 * @auther: liangyy
 * @className: ExceptionEnum
 * @date: 2018-09-26 19:26
 * @since: 1.0-SNAPSHOT
 */
public enum ExceptionEnum {

    NULL(-1, ""),

    SUCCESS(0, "成功"),

    /**
     * {@code 100 Continue}.
     * @see <a href="http://tools.ietf.org/html/rfc7231#section-6.2.1">HTTP/1.1: Semantics and Content, section 6.2.1</a>
     */
    CONTINUE(100, "Continue"),
    /**
     * {@code 101 Switching Protocols}.
     * @see <a href="http://tools.ietf.org/html/rfc7231#section-6.2.2">HTTP/1.1: Semantics and Content, section 6.2.2</a>
     */
    SWITCHING_PROTOCOLS(101, "Switching Protocols"),
    /**
     * {@code 102 Processing}.
     * @see <a href="http://tools.ietf.org/html/rfc2518#section-10.1">WebDAV</a>
     */
    PROCESSING(102, "Processing"),
    /**
     * {@code 103 Checkpoint}.
     * @see <a href="http://code.google.com/p/gears/wiki/ResumableHttpRequestsProposal">A proposal for supporting
     * resumable POST/PUT HTTP requests in HTTP/1.0</a>
     */
    CHECKPOINT(103, "Checkpoint"),

    // 2xx Success

    /**
     * {@code 200 OK}.
     * @see <a href="http://tools.ietf.org/html/rfc7231#section-6.3.1">HTTP/1.1: Semantics and Content, section 6.3.1</a>
     */
    OK(200, "OK"),
    /**
     * {@code 201 Created}.
     * @see <a href="http://tools.ietf.org/html/rfc7231#section-6.3.2">HTTP/1.1: Semantics and Content, section 6.3.2</a>
     */
    CREATED(201, "Created"),
    /**
     * {@code 202 Accepted}.
     * @see <a href="http://tools.ietf.org/html/rfc7231#section-6.3.3">HTTP/1.1: Semantics and Content, section 6.3.3</a>
     */
    HTTP_ACCEPTED(202, "Accepted"),
    /**
     * {@code 203 Non-Authoritative Information}.
     * @see <a href="http://tools.ietf.org/html/rfc7231#section-6.3.4">HTTP/1.1: Semantics and Content, section 6.3.4</a>
     */
    NON_AUTHORITATIVE_INFORMATION(203, "Non-Authoritative Information"),
    /**
     * {@code 204 No Content}.
     * @see <a href="http://tools.ietf.org/html/rfc7231#section-6.3.5">HTTP/1.1: Semantics and Content, section 6.3.5</a>
     */
    NO_CONTENT(204, "No Content"),
    /**
     * {@code 205 Reset Content}.
     * @see <a href="http://tools.ietf.org/html/rfc7231#section-6.3.6">HTTP/1.1: Semantics and Content, section 6.3.6</a>
     */
    RESET_CONTENT(205, "Reset Content"),
    /**
     * {@code 206 Partial Content}.
     * @see <a href="http://tools.ietf.org/html/rfc7233#section-4.1">HTTP/1.1: Range Requests, section 4.1</a>
     */
    PARTIAL_CONTENT(206, "Partial Content"),
    /**
     * {@code 207 Multi-Status}.
     * @see <a href="http://tools.ietf.org/html/rfc4918#section-13">WebDAV</a>
     */
    MULTI_STATUS(207, "Multi-Status"),
    /**
     * {@code 208 Already Reported}.
     * @see <a href="http://tools.ietf.org/html/rfc5842#section-7.1">WebDAV Binding Extensions</a>
     */
    ALREADY_REPORTED(208, "Already Reported"),
    /**
     * {@code 226 IM Used}.
     * @see <a href="http://tools.ietf.org/html/rfc3229#section-10.4.1">Delta encoding in HTTP</a>
     */
    IM_USED(226, "IM Used"),

    // 3xx Redirection

    /**
     * {@code 300 Multiple Choices}.
     * @see <a href="http://tools.ietf.org/html/rfc7231#section-6.4.1">HTTP/1.1: Semantics and Content, section 6.4.1</a>
     */
    MULTIPLE_CHOICES(300, "Multiple Choices"),
    /**
     * {@code 301 Moved Permanently}.
     * @see <a href="http://tools.ietf.org/html/rfc7231#section-6.4.2">HTTP/1.1: Semantics and Content, section 6.4.2</a>
     */
    MOVED_PERMANENTLY(301, "Moved Permanently"),
    /**
     * {@code 302 Found}.
     * @see <a href="http://tools.ietf.org/html/rfc7231#section-6.4.3">HTTP/1.1: Semantics and Content, section 6.4.3</a>
     */
    FOUND(302, "Found"),
    /**
     * {@code 302 Moved Temporarily}.
     * @see <a href="http://tools.ietf.org/html/rfc1945#section-9.3">HTTP/1.0, section 9.3</a>
     * @deprecated in favor of {@link #FOUND} which will be returned from {@code HttpStatus.valueOf(302)}
     */
    @Deprecated
    MOVED_TEMPORARILY(302, "Moved Temporarily"),
    /**
     * {@code 303 See Other}.
     * @see <a href="http://tools.ietf.org/html/rfc7231#section-6.4.4">HTTP/1.1: Semantics and Content, section 6.4.4</a>
     */
    SEE_OTHER(303, "See Other"),
    /**
     * {@code 304 Not Modified}.
     * @see <a href="http://tools.ietf.org/html/rfc7232#section-4.1">HTTP/1.1: Conditional Requests, section 4.1</a>
     */
    NOT_MODIFIED(304, "Not Modified"),
    /**
     * {@code 305 Use Proxy}.
     * @see <a href="http://tools.ietf.org/html/rfc7231#section-6.4.5">HTTP/1.1: Semantics and Content, section 6.4.5</a>
     * @deprecated due to security concerns regarding in-band configuration of a proxy
     */
    @Deprecated
    USE_PROXY(305, "Use Proxy"),
    /**
     * {@code 307 Temporary Redirect}.
     * @see <a href="http://tools.ietf.org/html/rfc7231#section-6.4.7">HTTP/1.1: Semantics and Content, section 6.4.7</a>
     */
    TEMPORARY_REDIRECT(307, "Temporary Redirect"),
    /**
     * {@code 308 Permanent Redirect}.
     * @see <a href="http://tools.ietf.org/html/rfc7238">RFC 7238</a>
     */
    PERMANENT_REDIRECT(308, "Permanent Redirect"),

    // --- 4xx Client Error ---

    /**
     * {@code 400 Bad Request}.
     * @see <a href="http://tools.ietf.org/html/rfc7231#section-6.5.1">HTTP/1.1: Semantics and Content, section 6.5.1</a>
     */
    BAD_REQUEST(400, "Bad Request"),
    /**
     * {@code 401 Unauthorized}.
     * @see <a href="http://tools.ietf.org/html/rfc7235#section-3.1">HTTP/1.1: Authentication, section 3.1</a>
     */
    UNAUTHORIZED(401, "Unauthorized"),
    /**
     * {@code 402 Payment Required}.
     * @see <a href="http://tools.ietf.org/html/rfc7231#section-6.5.2">HTTP/1.1: Semantics and Content, section 6.5.2</a>
     */
    PAYMENT_REQUIRED(402, "Payment Required"),
    /**
     * {@code 403 Forbidden}.
     * @see <a href="http://tools.ietf.org/html/rfc7231#section-6.5.3">HTTP/1.1: Semantics and Content, section 6.5.3</a>
     */
    FORBIDDEN(403, "Forbidden"),
    /**
     * {@code 404 Not Found}.
     * @see <a href="http://tools.ietf.org/html/rfc7231#section-6.5.4">HTTP/1.1: Semantics and Content, section 6.5.4</a>
     */
    NOT_FOUND(404, "Not Found"),
    /**
     * {@code 405 Method Not Allowed}.
     * @see <a href="http://tools.ietf.org/html/rfc7231#section-6.5.5">HTTP/1.1: Semantics and Content, section 6.5.5</a>
     */
    METHOD_NOT_ALLOWED(405, "Method Not Allowed"),
    /**
     * {@code 406 Not Acceptable}.
     * @see <a href="http://tools.ietf.org/html/rfc7231#section-6.5.6">HTTP/1.1: Semantics and Content, section 6.5.6</a>
     */
    NOT_ACCEPTABLE(406, "Not Acceptable"),
    /**
     * {@code 407 Proxy Authentication Required}.
     * @see <a href="http://tools.ietf.org/html/rfc7235#section-3.2">HTTP/1.1: Authentication, section 3.2</a>
     */
    PROXY_AUTHENTICATION_REQUIRED(407, "Proxy Authentication Required"),
    /**
     * {@code 408 Request Timeout}.
     * @see <a href="http://tools.ietf.org/html/rfc7231#section-6.5.7">HTTP/1.1: Semantics and Content, section 6.5.7</a>
     */
    REQUEST_TIMEOUT(408, "Request Timeout"),
    /**
     * {@code 409 Conflict}.
     * @see <a href="http://tools.ietf.org/html/rfc7231#section-6.5.8">HTTP/1.1: Semantics and Content, section 6.5.8</a>
     */
    CONFLICT(409, "Conflict"),
    /**
     * {@code 410 Gone}.
     * @see <a href="http://tools.ietf.org/html/rfc7231#section-6.5.9">
     *     HTTP/1.1: Semantics and Content, section 6.5.9</a>
     */
    GONE(410, "Gone"),
    /**
     * {@code 411 Length Required}.
     * @see <a href="http://tools.ietf.org/html/rfc7231#section-6.5.10">
     *     HTTP/1.1: Semantics and Content, section 6.5.10</a>
     */
    LENGTH_REQUIRED(411, "Length Required"),
    /**
     * {@code 412 Precondition failed}.
     * @see <a href="http://tools.ietf.org/html/rfc7232#section-4.2">
     *     HTTP/1.1: Conditional Requests, section 4.2</a>
     */
    PRECONDITION_FAILED(412, "Precondition Failed"),
    /**
     * {@code 413 Payload Too Large}.
     * @since 4.1
     * @see <a href="http://tools.ietf.org/html/rfc7231#section-6.5.11">
     *     HTTP/1.1: Semantics and Content, section 6.5.11</a>
     */
    PAYLOAD_TOO_LARGE(413, "Payload Too Large"),
    /**
     * {@code 413 Request Entity Too Large}.
     * @see <a href="http://tools.ietf.org/html/rfc2616#section-10.4.14">HTTP/1.1, section 10.4.14</a>
     * @deprecated in favor of {@link #PAYLOAD_TOO_LARGE} which will be
     * returned from {@code HttpStatus.valueOf(413)}
     */
    @Deprecated
    REQUEST_ENTITY_TOO_LARGE(413, "Request Entity Too Large"),
    /**
     * {@code 414 URI Too Long}.
     * @since 4.1
     * @see <a href="http://tools.ietf.org/html/rfc7231#section-6.5.12">
     *     HTTP/1.1: Semantics and Content, section 6.5.12</a>
     */
    URI_TOO_LONG(414, "URI Too Long"),
    /**
     * {@code 414 Request-URI Too Long}.
     * @see <a href="http://tools.ietf.org/html/rfc2616#section-10.4.15">HTTP/1.1, section 10.4.15</a>
     * @deprecated in favor of {@link #URI_TOO_LONG} which will be returned from {@code HttpStatus.valueOf(414)}
     */
    @Deprecated
    REQUEST_URI_TOO_LONG(414, "Request-URI Too Long"),
    /**
     * {@code 415 Unsupported Media Type}.
     * @see <a href="http://tools.ietf.org/html/rfc7231#section-6.5.13">
     *     HTTP/1.1: Semantics and Content, section 6.5.13</a>
     */
    UNSUPPORTED_MEDIA_TYPE(415, "Unsupported Media Type"),
    /**
     * {@code 416 Requested Range Not Satisfiable}.
     * @see <a href="http://tools.ietf.org/html/rfc7233#section-4.4">HTTP/1.1: Range Requests, section 4.4</a>
     */
    REQUESTED_RANGE_NOT_SATISFIABLE(416, "Requested range not satisfiable"),
    /**
     * {@code 417 Expectation Failed}.
     * @see <a href="http://tools.ietf.org/html/rfc7231#section-6.5.14">
     *     HTTP/1.1: Semantics and Content, section 6.5.14</a>
     */
    EXPECTATION_FAILED(417, "Expectation Failed"),
    /**
     * {@code 418 I'm a teapot}.
     * @see <a href="http://tools.ietf.org/html/rfc2324#section-2.3.2">HTCPCP/1.0</a>
     */
    I_AM_A_TEAPOT(418, "I'm a teapot"),
    /**
     * @deprecated See
     * <a href="http://tools.ietf.org/rfcdiff?difftype=--hwdiff&url2=draft-ietf-webdav-protocol-06.txt">
     *     WebDAV Draft Changes</a>
     */
    @Deprecated
    INSUFFICIENT_SPACE_ON_RESOURCE(419, "Insufficient Space On Resource"),
    /**
     * @deprecated See
     * <a href="http://tools.ietf.org/rfcdiff?difftype=--hwdiff&url2=draft-ietf-webdav-protocol-06.txt">
     *     WebDAV Draft Changes</a>
     */
    @Deprecated
    METHOD_FAILURE(420, "Method Failure"),
    /**
     * @deprecated
     * See <a href="http://tools.ietf.org/rfcdiff?difftype=--hwdiff&url2=draft-ietf-webdav-protocol-06.txt">
     *     WebDAV Draft Changes</a>
     */
    @Deprecated
    DESTINATION_LOCKED(421, "Destination Locked"),
    /**
     * {@code 422 Unprocessable Entity}.
     * @see <a href="http://tools.ietf.org/html/rfc4918#section-11.2">WebDAV</a>
     */
    UNPROCESSABLE_ENTITY(422, "Unprocessable Entity"),
    /**
     * {@code 423 Locked}.
     * @see <a href="http://tools.ietf.org/html/rfc4918#section-11.3">WebDAV</a>
     */
    LOCKED(423, "Locked"),
    /**
     * {@code 424 Failed Dependency}.
     * @see <a href="http://tools.ietf.org/html/rfc4918#section-11.4">WebDAV</a>
     */
    FAILED_DEPENDENCY(424, "Failed Dependency"),
    /**
     * {@code 426 Upgrade Required}.
     * @see <a href="http://tools.ietf.org/html/rfc2817#section-6">Upgrading to TLS Within HTTP/1.1</a>
     */
    UPGRADE_REQUIRED(426, "Upgrade Required"),
    /**
     * {@code 428 Precondition Required}.
     * @see <a href="http://tools.ietf.org/html/rfc6585#section-3">Additional HTTP Status Codes</a>
     */
    PRECONDITION_REQUIRED(428, "Precondition Required"),
    /**
     * {@code 429 Too Many Requests}.
     * @see <a href="http://tools.ietf.org/html/rfc6585#section-4">Additional HTTP Status Codes</a>
     */
    TOO_MANY_REQUESTS(429, "Too Many Requests"),
    /**
     * {@code 431 Request Header Fields Too Large}.
     * @see <a href="http://tools.ietf.org/html/rfc6585#section-5">Additional HTTP Status Codes</a>
     */
    REQUEST_HEADER_FIELDS_TOO_LARGE(431, "Request Header Fields Too Large"),
    /**
     * {@code 451 Unavailable For Legal Reasons}.
     * @see <a href="https://tools.ietf.org/html/draft-ietf-httpbis-legally-restricted-status-04">
     * An HTTP Status Code to Report Legal Obstacles</a>
     * @since 4.3
     */
    UNAVAILABLE_FOR_LEGAL_REASONS(451, "Unavailable For Legal Reasons"),

    // --- 5xx Server Error ---

    /**
     * {@code 500 Internal Server Error}.
     * @see <a href="http://tools.ietf.org/html/rfc7231#section-6.6.1">HTTP/1.1: Semantics and Content, section 6.6.1</a>
     */
    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
    /**
     * {@code 501 Not Implemented}.
     * @see <a href="http://tools.ietf.org/html/rfc7231#section-6.6.2">HTTP/1.1: Semantics and Content, section 6.6.2</a>
     */
    NOT_IMPLEMENTED(501, "Not Implemented"),
    /**
     * {@code 502 Bad Gateway}.
     * @see <a href="http://tools.ietf.org/html/rfc7231#section-6.6.3">HTTP/1.1: Semantics and Content, section 6.6.3</a>
     */
    BAD_GATEWAY(502, "Bad Gateway"),
    /**
     * {@code 503 Service Unavailable}.
     * @see <a href="http://tools.ietf.org/html/rfc7231#section-6.6.4">HTTP/1.1: Semantics and Content, section 6.6.4</a>
     */
    SERVICE_UNAVAILABLE(503, "Service Unavailable"),
    /**
     * {@code 504 Gateway Timeout}.
     * @see <a href="http://tools.ietf.org/html/rfc7231#section-6.6.5">HTTP/1.1: Semantics and Content, section 6.6.5</a>
     */
    GATEWAY_TIMEOUT(504, "Gateway Timeout"),
    /**
     * {@code 505 HTTP Version Not Supported}.
     * @see <a href="http://tools.ietf.org/html/rfc7231#section-6.6.6">HTTP/1.1: Semantics and Content, section 6.6.6</a>
     */
    HTTP_VERSION_NOT_SUPPORTED(505, "HTTP Version not supported"),
    /**
     * {@code 506 Variant Also Negotiates}
     * @see <a href="http://tools.ietf.org/html/rfc2295#section-8.1">Transparent Content Negotiation</a>
     */
    VARIANT_ALSO_NEGOTIATES(506, "Variant Also Negotiates"),
    /**
     * {@code 507 Insufficient Storage}
     * @see <a href="http://tools.ietf.org/html/rfc4918#section-11.5">WebDAV</a>
     */
    INSUFFICIENT_STORAGE(507, "Insufficient Storage"),
    /**
     * {@code 508 Loop Detected}
     * @see <a href="http://tools.ietf.org/html/rfc5842#section-7.2">WebDAV Binding Extensions</a>
     */
    LOOP_DETECTED(508, "Loop Detected"),
    /**
     * {@code 509 Bandwidth Limit Exceeded}
     */
    BANDWIDTH_LIMIT_EXCEEDED(509, "Bandwidth Limit Exceeded"),
    /**
     * {@code 510 Not Extended}
     * @see <a href="http://tools.ietf.org/html/rfc2774#section-7">HTTP Extension Framework</a>
     */
    NOT_EXTENDED(510, "Not Extended"),
    /**
     * {@code 511 Network Authentication Required}.
     * @see <a href="http://tools.ietf.org/html/rfc6585#section-6">Additional HTTP Status Codes</a>
     */
    NETWORK_AUTHENTICATION_REQUIRED(511, "Network Authentication Required"),

    CHECK_TOKEN_ERRER(600, "校验token失败"),

    SYSTEM_ERROR(999, "系统异常"),


    /*
     * 2xx: 表示请求成功地接收
     */
    CREATED_SUCCESS(200001, "创建成功"),
    ACCEPTED(200002, "请求已经被接受用来处理"),
    UPDATE_SUCCESS(200003, "更新成功"),
    UPLOAD_SUCESS(200004, "上传成功"),

    /*
     * 3xx: 为完成请求客户需进一步细化请求
     */


    /*
     * 4xx：客户端的请求有错误
     */
    // 400: 通用校验
    LESS_PARAMETER(400001, "缺少参数"),
    ILLEGAL_PARAMETER(400002, "参数不合法"),
    BEGIN_MORE_THAN_END(400003, "开始时间大于结束时间"),
    DATE_TIME_NOT_NULL(400004, "时间日期不能为空"),
    PHONE_NOT_NULL(400005, "手机号不能为空"),
    VERIFY_CODE_NOT_NULL(400006, "验证码不能为空"),
    VERIFY_CODE_CHECK_ERROR(400007, "验证码校验错误"),
    VERIFY_CODE_EXPIRE(400008, "验证码无效，请重新获取"),
    FAILED_TO_SEND_SMS_REPEAT(400009, "发送失败：重复请求。"),
    FAILED_TO_SEND_SMS(400010, "发送手机验证码失败"),
    FAILED_TO_SEND_EMAIL(400011,"发送邮件失败"),
    FAILED_ADMIN(400012,"admin用户信息错误"),
    EMPTY_EMAIL(400013,"没有邮箱信息"),
    FAILED_TO_SUBMIT(400014, "此流程您已审批，请勿重复审批"),
    WORKFLOW_NOT_EXIST(400015, "该审批流不存在"),
    WORKFLOW_PROCESSOR_NOT_EXIST(400016, "该审批流处理器不存在"),
    UNKNOWN_WORKFLOW_OPERATE(400017, "未知的流程操作"),
    DELETE_ERROR(400018, "已进入审批流程中，无法撤销"),
    WORKFLOW_TASK_NOT_EXSIT(400019, "审批流任务信息不存在"),
    WORKFLOW_INSTANCE_NOT_EXSIT(400020, "流程实例不存在"),
    UNKNOWN_WORKFLOW_TYPE(400021, "未知的流程类型"),
    UNKNOWN_SUB_WORKFLOW_TYPE(400022, "未知的流程子类型"),
    TASK_INSTANCE_NOT_MATCH(400023, "审批任务对应流程实例ID不匹配"),
    WORKFLOW_HAS_FINISHED(400024, "该审批流已经结束"),
    WORKFLOW_DELETE_FAILED(400025, "审批流撤销失败"),
    WORKFLOW_MODIFY_PARAMS_FAILED(400026, "审批流修改参数失败"),
    WORKFLOW_OLD_FAILED(400026, "流程改造前发起的审批流不支持替换审批人"),
    GET_INSTANCE_TASK_FAIL(400027, "获取流程任务失败"),
    WORKFLOW_REPETITION_FAIL(400028, "会签组不允许出现重复的审批人"),
    WORKFLOW_EQUAL_FAIL(400028, "替换人和被替换人为同一人"),
    DATA_SAME_TO_UPDATE(400029, "数据未做修改，无需更新"),
    CHOOSE_WORKFLOW_TYPE_PLEASE(400030, "请选择审批类型"),
    Work_NUM_NOT_LETTER(400031,"仅能在工号前增加英文字母"),

    // 401: 文件相关（文件，图片）
    FAILED_TO_BUILD_QCCODE(401001,"二维码文件创建失败"),
    FAILED_TO_DELE_QRCODE(401001,"删除二维码文件失败"),
    FAILED_TO_BUILD_TRIP_APPLY(401003,"出差申请单生成失败"),
    FAILED_TO_DELETE_TRIP_APPLY(401004,"出差申请单删除失败"),
    FAILED_TO_UPLOAD_TRIP_APPLY(401004,"出差申请单上传失败"),
    UPLOAD_FILE_IS_NOT_NULL(401005,"上传文件不能为空"),

    // 402：权限及登陆相关（用户、部门、员工、角色等）
    EMPLOYEE_INFO_IS_NULL(402001, "员工信息为空"),
    EMPID_IS_NULL(402002,"empId is null"),
    USER_INFO_IS_NULL(402003, "用户信息为空"),
    ROLE_ID_NOT_NULL(402004, "角色ID不能为空"),
    USER_NOT_EXIST(402005, "用户不存在"),
    CLIENT_ID_IS_NULL(402006, "用户推送ID为空"),

    USER_CREDENTIAL_NOT_NULL(402007, "用户凭证为空"),
    USER_ID_IS_NULL(402008, "用户ID为空"),
    DEPART_INFO_IS_NULL(402009, "部门信息为空"),
    DEPART_INFO_NOT_NULL(402010, "部门信息不能为空"),
    DEPART_NAME_IS_NULL(402011, "部门名称为空"),
    PARENT_DEPART_ID_IS_NULL(402012, "父部门Id为空"),
    PARENT_DEPART_INFO_NOT_EXIST(402013, "父部门信息不存在"),
    DEPART_ID_IS_NULL(402014, "部门ID为空"),
    DEPART_ID_NOT_NULL(402015, "部门ID为不能为空"),
    DEPART_NOT_EXIST(402016, "该部门不存在"),
    PARENT_DEPART_ID_NOT_NULL(402017, "父部门Id不能为空"),
    EMPLOYEE_COMPANY_IS_NULL(402019, "该部门所有员工公司信息为空"),
    ROLE_NAME_NOT_NULL(402018, "角色名称不能为空"),
    COMPANY_TABLE_IS_NULL(402020, "没有公司信息"),
    WORK_NUM_EXISTED(402021, "工号已经存在"),
    EXIST_MANAGER(402022,"员工中包含主管，不能批量调整部门"),
    DEPART_NEEDS_PERSON(402023,"已存在的部门必须有人"),
    DEPART_DING_ID_NULL(402025,"部门钉钉信息缺失，推送失败"),
    CANDIDATE_IS_NULL(402026, "候选人信息为空"),
    EMAIL_IS_NULL(402027, "邮箱信息为空，请联系HR补录邮箱"),
    WORKFLOW_STATUS_IS_WRONG(402028, "该申请未审批通过，无法生成行程单"),
    EMAIL_IS_WRONG(402029, "邮箱信息错误"),
    GOVMERMEMNT_AUTH_NONE(402030,"您无权限"),
    TOP_DEPART_CAN_NOT_DELETE(402031, "钉钉不允许删除跟部门"),
    ID_CARD_LENGTH_ERROR(402032, "身份证位数错误"),
    EMAIL_FORMAT_IS_ILLAGAL(402033, "邮箱格式校验错误"),
    EMAIL_HAS_BEEN_USED(402034, "邮箱格已经被使用"),
    ID_CARD_CHECK_ERROR(402035, "身份证号码校验失败"),
    MAIN_DEPART_INFO_IS_NULL(402036, "主部门信息为空"),


    // 403：审批、流程
    MEMBER_ID_IS_NULL(403001,"memberId不能为空"),
    INSTANCE_ID_IS_NULL(403002, "审批编号不能为空"),
    WORKTASKID_IS_NULL(403003, "任务不能为空"),
    ISSTAR_IS_NULL(403004, "isStar_is_null"),
    EMPLOYEEID_ID_IS_NULL(403005,"员工ID为空"),
    WORKFLOW_APPROVE_ERROR(403006,"无法找到下级审批人，请联系hr设置。"),
    EMPLOYEE_APPLY_NOT_EXIST(403006,"员工申请信息不存在"),
    EMPLOYEE_TRANSFER_NOT_EXIST(403006,"员工调整信息不存在"),
    TRANSFER_APPLY_NOT_EXIST(403007,"员工调岗或调级信息不存在"),
    USER_WORKFLOW_TASK_NOT_EXIST(403008, "用户审批流任务信息不存在"),
    WORKFLOW_TASK_COMPLETE_AUTH_FAILED(403009, "审批流任务鉴权失败"),
    WORKFLOW_TASK_COMPLETE_AUTH_ERROR(403010, "审批流任务鉴权异常"),
    WORKFLOW_MAX_CC_NUM_ERROR(403011, "超出流程抄送人最大数量限制"),
    EMPLOYEE_APPLY_UPDATE_FAILED(4030012,"流程申请更新失败"),
    GET_INSTANCE_PARAMS_FAILED(4030013,"获取流程实例参数失败"),
    DING_ID_IS_NULL(4030014,"dingId不能为空"),


    // 404：假期 leave
    SELECT_PARAM_IS_NULL(404001,"员工姓名或工号不能为空"),
    LEAVE_APPROVE_IS_NULL(404002,"员工暂无请假记录"),
    CHECK_LEAVE_CONDITION_FAIL(404003,"请假条件校验失败"),
    LEAVE_CONDITION_NOT_MEET(404004,"该类型请假条件不满足"),


    // 405：出差trip
    TRIPVO_IS_NULL(405001, "tripVO is null"),
    TRIP_ID_IS_NULL(405002, "差旅编号不能为空"),
    TRIP_DETAIL_IS_NULL(405003, "出差详情为空"),
    TRIP_VEHICLE_TYPE_IS_NULL(405004, "交通工具字典查询失败"),
    TRIP_CHECK_EXPORT_ERROR(405005, "行程单在出差结束第3天10点后才可以导出"),
    TRIP_ORDER_AUDIT_ERROR(405006, "调用携程接口异常, 请稍后重试"),
    TRIP_CHECK_NO_CONTRACT(405007, "找不到签约公司，请联系HR"),


    // 406：外出 outwork


    // 407：database数据库操作相关异常码


    // 408：绩效相关异常码
    PERFORMANCE_HRBP_IS_NULL(408001,"未设置审批组--绩效HRBP,请联系HR设置"),
    PERFORMANCE_IS_NOT_HRBP(408002,"非HRBP，无权查询管理部门"),
    PERFORMANCE_IS_NOT_HRBP_AUTHORITY(408003,"没有部门查询权限"),
    PERFORMANCE_SUBMIT_NOT_AUTH(408004,"没有权限，您不是该部门主管"),
    PERFORMANCE_REVOKE_FAIL_OR_NOT_AUTH(408005,"绩效撤回失败或没有权限"),
    PERFORMANCE_REJECT_FAIL_OR_NOT_AUTH(408006,"绩效退回失败或没有权限"),
    PERFORMANCE_DEPART271_PARENTDEPART_IS_NO_LEADER(408007,"上级部门未设置领导，不能提交！请联系相关HR为上级部门设置领导"),
    PERFORMANCE_DEPART271_IS_NO_PARENTDEPART(408008,"该部门没有上级部门，不能提交！"),
    PERFORMANCE_HAVE_CHILDDEPART_NOT_SUBMIT(408009,"有下属部门未提交271，不能提交！请先通知下属部门提交271"),
    PERFORMANCE_STATUS_CAN_NOT_SUBMIT(408010,"有未确认评分的员工绩效，不能提交！"),
    PERFORMANCE_APPEAL_DEPART_NOT_ROLE(408011,"员工所在部门没有管理的绩效HRBP"),
    PERFORMANCE_OPERATE_NOT_AUTH(408012,"您暂无操作权限"),
    PERFORMANCE_SCORE_CLOSED_CANNOT_APPEAL(408013,"绩效成绩未开放，不能申诉"),
    PERFORMANCE_PERIOD_ALREADY_FROZEN(408014,"当前绩效周期已冻结，不允许生成卡片。请联系绩效管理员"),
    PERFORMANCE_PERIOD_UN_FROZEN_OR_SCORE_OPENED(408015,"当前绩效周期未冻结或成绩已开放"),
    PERFORMANCE_PERIOD_ALREADY_APPRROVE(408016,"当前绩效周期绩效已审批通过"),
    PERFORMANCE_GOAL_IMPORT_TYPE_UNKNOWN(408017, "绩效目标未知的导入类型"),
    PERFORMANCE_GOAL_SUBMIT_TYPE_UNKNOWN(408018, "绩效目标未知的提交类型"),

    //500: 通用
    DEFAULT_ERROR(500001, "未知异常"),
    DEFAULT_BUSINESS_ERROR(2, "业务异常"),
    DEFAULT_COMPONENT_ERROR(3, "模块异常"),
    /* 未知的组件异常 */
    DEFAULT_DATABASE_ERROR(6, "数据库异常"),
    DEFAULT_API_ERROR(7, "接口异常"),
    DEFAULT_FILE_ERROR(8, "文件异常"),
    /* 未知的业务类异常 */
    DEFAULT_AUTHORITY_ERROR(15, "权限异常"),
    DEFAULT_LOGIN_ERROR(16, "登陆异常"),
    DEFAULT_VERIFY_ERROR(17, "校验异常"),


    SERVICE_TEMPORARILY_UNAVAILABLE(30, "服务暂不可用"),
    NO_PERMISSION_TO_ACCESS(31, "无权限访问"),
    LOGIN_ERROR(500101, "登陆异常"),
    NOT_LOGIN(500102, "没有登陆"),
    LOGIN_FAILE(500103,"登陆失败"),
    LOGIN_SUCCESS(500104, "登陆成功"),
    INVALID_CLIENT(500105, "用户认证失败"),
    INVALID_USER(500106, "无效的用户"),
    USER_AND_PWD_NOT_NULL(107, "用户名和密码不能为空"),
    PASSWORD_ERROR(500108, "密码错误"),
    VERIFICATION_CODE_ERROR(500109, "验证码错误"),
    ACCESS_TOKEN_EXPIRED(500110, "access token过期"),
    ACCESS_TOKEN_INVALID(500111, "无效的access token"),
    SESSION_KEY_EXPIRED(500112, "session过期"),
    SESSION_KEY_INVALID(500113, "无效的session"),
    PARAMETER_NOT_NULL(500121, "参数不能为空"),
    PARAMETER_OBJECT_NOT_NULL(500122, "参数对象不能为空"),
    FAILED_TO_SEND_MESSAGE(500600, "发送消息失败"),
    INSERT_DATA_FAIL(500601, "添加失败"),
    UPDATE_DATA_FAIL(500602, "更新失败"),
    DELETE_DATA_FAIL(500603, "删除失败"),
    SELECT_DATA_NULL(500604, "数据不存在"),
    VALIDATION_FAIL(500700, "校验失败"),
    UNKNOWN_DATA_STORE_ERROR(500800, "未知的存储操作错误"),
    INVALID_OPERATION(500801, "无效的操作方法"),
    DATA_STORE_ALLOWABLE_QUOTA_WAS_EXCEEDED(500802, "数据存储空间已超过设定的上限"),
    DATABASE_ERROR_OCCURRED(500803, "数据库操作出错，请重试"),
    NO_SUCH_APPLICATION_EXISTS(500900, "访问的应用不存在"),
    OBTAIN_CURRENT_IP_ADDRESS_FAIL(500999, "获取IP失败"),
    // 501：文件相关
    FAILED_TO_GET_FILE(501001, "获取文件失败"),
    FAILED_TO_GENERATE_PIC(501002, "生成图片失败"),
    FILE_FORMAT_INCORRECT(501003, "文件格式不正确"),
    FILE_FORMAT_NOT_PDF(501004, "文件格式不是PDF"),
    IMG_FORMAT_INCORRECT(501005, "图片格式不正确"),
    IMG_SIZE_OVER(501006, "图片过大"),
    FILE_SIZE_OVER(501007, "文件过大"),
    FILE_FORMAT_UNRECOGNIZED(501008, "文件格式无法识别"),

    // 502：用户权限及登陆相关（用户、角色、权限、资源等）
    NOT_EXIST_USER(502005, "该用户不存在"),
    NOT_EXIST_COMPANY(502010, "该用户未设置所属公司"),
    NOT_EXIST_DEPARTMENT(502011, "该用户未设置所属部门"),
    NOT_EXIST_LEADER(502012, "该部门未设置主管"),
    NO_LEADER(502006, "该部门未设置主管"),
    QUERY_DEPARTMENTS_IS_NULL(502013, "该部门已被调整"),
    USER_OF_DING_NOT_EXIST(502014, "用户对应的钉钉id不存在"),
    OBTAIN_USER_INFO_FAIL(502015, "获取用户信息失败"),
    NEW_DEPART_NO_SET_MANAGER(502016, "该部门未设置主管"),
    DINGID_OF_PARENT_DEPART_NOT_EXIST(502017, "父部门dingId不存在"),
    DING_CREATE_DEPART_FAIL(502018, "钉钉创建新部门失败"),
    STAFF_SELECTED_AGAINST_RULE(502019, "所选员工不符合规则"),
    DING_CREATE_DEPART_FAIL_2(502020,"钉钉创建部门响应失败"),
    DING_UPDATE_USER_FAIL(502021, "钉钉更新用户失败"),
    DING_USER_NOT_EXIST(502022, "钉钉不存在此用户"),
    DING_DEPARTMENT_NAME_CHANGED_FAILURE(502023,"钉钉更新部门名称失败"),
    NO_SET_MANAGER(502024, "该部门未设置主管"),
    IS_OLD_MANAGER(502025, "当前部门主管不允许调整部门"),
    EQUAL_TO_OLD_DEPART(502026, "当前所在部门不可在调整列表中"),
    WRONG_IN_DEPARTS(502027, "有兼岗的必须为某个部门的主管"),
    DEPART_OF_DING_NOT_EXIST(502028, "部门对应的钉钉id不存在"),
    DING_ID_WRONG_FORMAT(502029, "钉钉id格式有误"),
    DING_BATCH_CHANGE_DEPART_FAIL(502030,"钉钉批量调整部门失败"),
    DING_BATCH_CHANGE_DEPART_FAIL_2(502031,"钉钉批量调整部门响应失败"),
    WRONG_IN_CHANGE_MANAGER(502032,"部门主管替换失败"),
    WRONG_IN_CHANGE_DINGDING_MANAGER(502033,"钉钉替换主管失败"),
    WRONG_IN_CREATE_DING_RESPONSE(502034,"创建钉钉响应异常"),
    WRONG_IN_CHANGE_PARENT_DINGDING(502035,"更改父部门失败"),
    EMPLOYEE_NEED_DEPART(502036,"员工必须属于某部门"),
    NOT_DEPART_LEADER(502037,"此用户不是部门领导"),
    NO_WORKSTATUS(502038, "员工没有雇佣状态"),
    NOT_EXIST_MAIN_DEPART(502039, "未设置主部门，请联系HRBP"),
    EMPLOYEE_NOT_RANK(502040, "该员工没有设置职级"),
    EMPLOYEE_NOT_SALARY(502041, "该员工没有薪资方案"),
    NO_EMPLOYMENT_TYPE(502042,"员工没有员工类型"),
    EMPLOYEE_ENTRY_REJECTED_BY_DINGTALK(502043, "此员工在钉钉隐私中开启了“团队添加时需确认”的功能，导致入职失败"),
    EMPLOYEE_ENTRY_REJECTED_BY_DINGTALK_FORMOBILE(502044, "该员工已从钉钉入职，员工档案信息待次日自动更新，无需再次点击确认入职"),
    CAN_NOT_DELETE_DEPART_FOR_CHILDREN(502045, "删除部门时不能有子级部门"),
    CAN_NOT_DELETE_DEPART_FOR_EMPLOYEE(502046, "删除部门时不能含有人员"),
    DELETE_DEPART_DINGDING_ERROR(502047, "钉钉删除部门失败"),
    EMPLOYEE_NEED_SAME_DEPART(502048,"批量调整员工必须属于相同部门"),
    EMPLOYEE_NEED_ONE_DEPART(502049,"批量调整员工必须为单部门"),
    EMPLOYEE_DEPART_NOT_EQUAL(502050,"员工ID与部门ID不匹配"),
    EMPLOYEE_HAS_DIMISSION(502051, "操作失败,该员工已离职"),
    EMPLOYEE_INFO_NOT_EXSIT(502052, "员工信息不存在"),
    SRC_DIRECT_MANAGER_NOT_EXIST(502053, "原部门直属主管信息不存在"),
    SRC_SECOND_MANAGER_NOT_EXIST(502054, "原部门二级主管信息不存在"),
    SALARY_AGENT_NOT_EXIST(502055, "薪酬专员信息不存在"),
    SALARY_MANAGER_NOT_EXIST(502056, "薪酬主管信息不存在"),
    DEST_COMMISSAR_NOT_EXIST(502057, "目标部门政委信息不存在"),
    SRC_COMMISSAR_NOT_EXIST(502058, "原部门政委信息不存在"),
    DEST_DIRECT_MANAGER_NOT_EXIST(502059, "目标部门直属主管信息不存在"),
    DEST_SECOND_MANAGER_NOT_EXIST(502060, "目标部门二级主管信息不存在"),
    CENTER_MANAGER_NOT_EXIST(502061, "员工中心负责人信息不存在"),
    RELATIONSHIP_NOT_EXIST(502062, "员工关系信息不存在"),
    DEPARTMENT_ID_NOT_EXIST(502063, "部门ID非法"),
    EMPLOYEE_INFO_INVALID(502064, "员工信息无效"),
    GET_MANAGER_INFO_FAIL(502065, "获取主管信息失败"),
    GET_DMISSION_DATE_FAIL(502066, "获取员工离职日期失败"),
    CURRENT_DEPART_NO_LEADER(502067, "当前绩效未查询到主管信息，请联系hr重新生成绩效"),
    DEPART_NEED_SAME_DEPART(502068, "调整排序的部门必须属于同一部门下"),
    TRANSFER_SIGN_USER_INVALID(502069, "转签用户信息无效"),

    // 503：审批
    FAILED_TO_GET_APPROVER(503001, "获取审批人信息失败"),
    APPROVAL_IS_LESS_THAN_3(503002, "审批人个数少于3"),
    DELETE_ROLE_USER_NOT_YET(503003, "该成员还有未完成的审批,暂时不能删除"),
    LESS_GOAL_APPLY(503004, "此目标未提交"),
    SUBMIT_LESS_APPROVE_PROCESS(503005, "此提交没有进入审批流程"),
    NOT_FOUND_FINANCE(503006, "没有设置财务审批人"),
    NOT_FOUND_CEO(503007, "没有设置CEO审批"),
    NOT_FOUND_FINANCE_ROLE(503008, "没有设置财务审批组"),
    PLEASE_SET_FIRST_APPROVAL(503009, "请设置一级审批人"),
    PLEASE_SET_SECOND_APPROVAL(503010, "请设置二级审批人"),
    PLEASE_SET_THIRD_APPROVAL(503011, "请设置三级审批人"),
    PLEASE_SET_PROCESS_KEY(503012, "系统未设置流程图"),
    COLOR_IS_MARKED(503013,"该用户已经标记颜色"),
    VERSION_INVALID(503014,"已失效，重新标记"),
    NOT_FOUND_PERFORMANCE_HRBP_ROLE(503017,"没有设置绩效HRBP审批组"),


    // 504：假期 leave
    NOT_EXIST_LEAVE_TYPE(504001, "假期类型不存在"),
    NO_POSITIVE_PERSONAL_LEAVE(504002, "未转正员工暂不能请年假和亲情假"),
    NOT_EXIST_SALARY(504003, "该用户没有设置薪酬方案"),
    ONLY_MAN_APPLY(504004, "小姐姐们不能请这个假"),
    ONLY_WOMAN_APPLY(504005, "大佬爷们不能请这个假"),
    NEED_APPENDIX(504006, "请上传凭证"),
    NO_ENOUGH_BALANCE(504007, "假期申请时长过长,超过假期可用余额"),
    START_MUST_BE_WORKTIME(504008, "开始时间必须为工作日，请再确认一下"),
    CONFLICT_WITH_OTHER_LEAVES(504009, "在该时间段有申请中或通过的假期"),
    START_NOT_BEFORE_MONTH(504010, "假期开始月份不得小于本月"),
    NOT_ANNUAL_LINK_MARRIAGE(504011, "婚假和年假不能连续请"),
    PLEASE_FILL_MARRY_DATE(504012, "请填写结婚登记日"),
    START_BE_END_OF_MATERNITY(504013, "开始时间必须等于某个已休产假结束时间"),
    KINSHIP_ONLY_ONE_DAY(504014, "亲情假只能请一天"),
    NOT_SET_KINSHIP(504015, "这一天没有设置亲情假"),
    ALREADY_APPLY_MARRIAGE(504016, "你以前已申请过婚假，不能再次申请"),
    START_AWAY_FIVE_DAYS(504017, "开始时间距现在至少要5个工作日"),
    END_AWAY_ONE_YEAR(504018, "结束时间要在一年的范围内"),
    NOT_AWAY_TEN_MONTHS(504019, "现在时间距上次产假结束日期不到10个月,不能休产假"),
    START_AWAY_FIFTEEN_DAYS(504020, "假期开始时间最早是预产期前15天"),
    WIFE_NOT_OLD_ENOUGH(504021, "妻子现在未满24周岁，暂不能请陪产假"),
    ALREADY_APPLY_PATERNITY(504022, "你以前已申请过陪产假，不能再次申请"),
    START_AWAY_ONE_MONTH(504023, "开始时间要在子女生产日期一个月内"),
    END_AWAY_ONE_MONTH(504024, "结束时间要在子女生产日期一个月内"),
    ONLY_THIS_MONTH(504025, "产检假必须本月休完，不能跨月"),
    SICK_OVER_FORTY_FIVE(504026, "一次性病假申请超过45天，当年无年假"),
    ADDED_SICK_OVER_SIXTY_FIVE(504027, "累计病假申请超过65天，当年无年假"),
    AFFAIR_OVER_TWENTY_FIVE(504028, "一次性事假申请超过25天，当年无年假"),
    ADDED_AFFAIR_OVER_SIXTY_FIVE(504029, "累计事假申请超过65天，当年无年假"),
    EXIST_KINSHIP_VACATION(504030, "这一天已经是申请中或已经可用的亲情假"),
    SELECT_TOO_MUCH(504031, "亲情假最多设置5天"),
    NOT_WIFE_AGE(504032, "系统没有查询到妻子年龄，无法确定陪产假能否申请"),
    NO_VACATION_AMOUNT_DATA(504033, "系统查询不到假期额度"),
    START_NOT_BEFORE_PRENATAL(504034, "假期开始时间不得小于怀孕时间"),
    CHART_NO_EMP(504035, "未查询到员工，无需导出报表"),
    CHOOSE_LEAVE_TYPE(504036, "请勾选假期类型"),
    KINSHIP_NOT_SUPPORT(504037, "暂不能申请亲情假"),
    EXCESS_THREE_MONTH(504037,"选择时间段不得超过3个月"),
    CALENDAR_INFO_INIT_FAILED(504038, "假期信息初始化失败"),
    UNSUPPORTED_LEAVE_TYPE(504039, "不支持的假期类型"),

    // 505：出差 trip
    TRIP_DETAIL_NULL(505001,"出差行程不能为空"),
    TRIP_DETAIL_PRE_NEXT_CITY_NOTSAME(505002,"出差前一行程目的地不等于后一行程出发地"),
    TRIP_DETAIL_PRE_NEXT_TIme_NOTSAME(505003,"出差前一行程结束时间不等于后一开始时间"),
    TRIP_DETAIL_TRIPDAYS(505004,"出差天数为空"),

    // 506：外出 outwork


    // 507：database数据库操作相关异常码
    DATABASE_OPERATION_ERROR(507000, "数据库操作失败"),
    UPDATE_DEPART_PARENT_FAIL(507001, "更改部门结构失败"),
    UPDATE_DEPART_INFO_FAIL(507002, "更新部门信息失败"),
    UPDATE_PARENT_DEPART_FAIL(507003, "update parent departmentInfo fail"),
    INSERT_USER_PART_FAIL(507004, "添加用户部门失败"),
    INSERT_MONEY_OVER_FAIL(507005, "金额超出范围"),
    SELECT_EMPLOYEE_INFO_FAIL(507006, "数据库查询部门信息为空"),
    SELECT_EMPLOYEE_DEPART_FAIL(507007, "数据库查询员工部门信息为空"),

    // 508：绩效相关异常码
    GOAL_TYPE_NOT_EXIST(508001, "考核目标类型不存在"),
    GOAL_STATUS_NOT_EXIST(508002, "考核目标状态不存在"),
    GOAL_ID_NOT_NULL(508003, "考核目标ID为空"),
    GOAL_DRAFT_SAVE_FALSE(508004, "保存草稿失败"),
    GOAL_DRAFT_DELETE_FALSE(508005, "删除草稿失败"),
    GOAL_SUBMIT_FALSE(508006, "提交目标失败"),
    GOAL_CONFIRM_FALSE(508007, "确认目标失败"),
    GOAL_REJECT_FALSE(508008, "拒绝目标失败"),
    GOAL_REVOKE_FALSE(508009, "撤销目标失败"),
    USER_DEPART_NOT_MATCH(508010, "用户和部门不匹配"),
    NOT_DRAFT_CANNOT_DELETE(508011, "此目标不能删除"),
    SUBMIT_DEPAER_NOT_MATCH(508011, "提交目标的部门不匹配"),
    ACTIVITY_ERROR(508012,"审批流异常"),
    GOAL_SCORE_FALSE(508013, "目标评分失败"),
    PERFORMANCE_CONFIRM_ERROR(508014, "确认失败，所选部门中包含主管未评分员工"),
    PERFORMANCE_SUBMITDEPART_NOT_EXIST(508015, "所选部门未设置上级提交部门,请联系HRBP"),
    PERFORMANCE_DEPART_NOT_EXIST(508015, "所选部门不存在"),
    DEPART_IS_NOT_CONFIRMED(508016, "开始审核失败，存在未确认的子部门"),
    DEPART_IS_NOT_SUBMITED(508017, "开始审核失败，存在下级未提交部门或提交部门状态异常"),
    DEPART_IS_NOT_SUBMITED_2(508018, "开始审核失败，存在下级未提交部门"),
    GOAL_CANNOT_EDIT(508019, "此目标处于不能修改状态"),
    GOAL_CANNOT_SUBMMIT(508020, "目标已经不能提交"),
    GOAL_CANNOT_CANCLE_SCORE(508021, "自评分已经不能取消"),
    GOAL_CANNOT_REVOKE(508022, "目标已经被确认过不能撤销"),
    GOAL_CANNOT_CONFIRM(508023, "目标已经撤销不能被确认"),
    LEADER_CANNOT_SCORE(508024, "员工评分没有提交，无法进行评分"),
    SELF_SCORE_CANNOT_EDIT(508025, "此评分处于不能修改状态"),
    PERFORMANCE_UNCOMMIT_CANNOT_CONFIRM(508026, "绩效未提交，不能被确认"),
    PERFORMANCE_NOT_EXIST(508027, "绩效不存在"),
    PERFORMANCE_SCORE_CONFIRM_FALSE(508028, "绩效评分确认失败"),
    PERFORMANCE_UNAPPROVAL_CANNOT_INTERVIEW(508029, "绩效未审批通过，不能面谈"),
    PERFORMANCE_INTERVIEW_CONFIRM_FALSE(508030, "绩效面谈完成确认失败"),
    PERFORMANCE_MANAGER_REVIEW_CANNOT_CANCEL(508031, "主管已审核，不能被撤回"),
    GOAL_NOT_EXIST(508032,"目标不存在"),
    PERFORMANCE_NOT_FINAL_APPROVAL(508033,"该员工绩效未终审通过，不能进行确认"),
    GOAL_IS_REJECT_CAN_NOT_SUBMIT(508034,"该目标已被拒绝，不能提交"),
    HAVE_GOAL_IS_REJECT_CAN_NOT_SUBMIT(508035,"存在被拒绝的目标，请修改后再次提交"),
    PERFORMANCE_INTERVIEW_CONFIRM_UNAUTHORIZED(508036,"您不是本人，不能确认。"),
    PERFORMANCE_STATUS_IS_INTERVIEW_CANNOT_APPEAL(508037,"已确认或申诉中的绩效不能申诉"),
    PERFORMANCE_HAVE_EMPLOYEE_UN_FINAL_APPROVAL(508038, "当前绩效周期有员工未终审通过"),



    // 509: 转正异常码
    PROMOTION_USER_NOT_EXIST(509001,"员工不存在"),
    PROMOTION_USER_NOT_TRY(509002,"员工不在试用期"),
    PROMOTION_USER_INVALID_LENGTH(509003,"不能超过800字"),
    PROMOTION_GET_APPROVE_ERROR(509004, "获取审批人信息失败"),
    PROMOTION_GET_HR_ERROR(509005, "获取HRBP信息失败"),
    PROMOTION_ENTRY_DATE_NOT_EXIST(509006,"入职日期不存在"),
    PROMOTION_TURN_DATE_NOT_EXIST(509007,"转正日期不存在"),
    PROMOTION_GET_APPROVE_DETAIL_ERROR(509008, "获取审批人详细信息失败"),
    PROMOTION_GET_APPROVE_DEPART_ERROR(509009, "获取审批人部门信息失败"),
    PROMOTION_APPENDIX_COUNT_ERROR(509010, "附件总数量不能超过10个"),
    PROMOTION_SEND_NOTIFY_ERROR(509011, "发送定时通知失败"),
    PROMOTION_SEND_RESULT_ERROR(509012, "发送结果通知失败"),
    PROMOTION_UPDATE_STATUS_ERROR(509013, "用户转正失败"),
    PROMOTION_REPEAT_APPLY_ERROR(509014, "您有相同流程在进行中，不可重复发起"),
    PROMOTION_USER_NO_AUTH(509015, "您的员工类型/状态，不可发起此流程"),

    // 510: 入职相关
    ALREADY_ENTRY(510001, "已入职,不能再继续操作"),
    REJECT_OFFER(510002, "已拒绝offer"),
    WORK_NUM_IS_SPECIAL(510003,"工号为特殊工号"),
    WORK_NUM_IS_EXITES(510004,"工号已经存在"),
    EMP_IS_EXITES(510005,"员工已存在"),
    WORK_NUM_LENGTH_WORNG(510006,"工号位数不对"),
    WORK_NUM_MUST_NUM(510007,"必须为纯数字"),
    RANK_REJECT(510008,"未匹配到所填职级"),
    SAVE_CANDIDATE_INFO_ERROR(510009,"候选人信息存档失败"),
    CANDIDATE_INFO_INIT_ERROR(510010,"候选人信息初始化存档失败"),
    CANDIDATE_INFO_NAME_ERROR(510011,"用户姓名拼音转换异常"),
    CERT_EXIST_FORMAL(510012,"持有该证件号码的员工已入职，请更换后重试"),
    PHONE_EXIST_FORMAL(510013,"企业内已存在该手机号，请更换后重试"),
    CERT_EXIST_CANDIDATE(510014,"已有待入职候选人用此证件号码登记过，请更换后重试"),
    PHONE_EXIST_CANDIDATE(510015,"已有待入职候选人用此手机号登记过，请更换后重试"),



    // 511: 调岗相关
    CHANGE_DEPARTMENT_ERROR_USER(511001, "申请人信息错误"),
    CHANGE_DEPARTMENT_ERROR_POSITION(511002, "新岗位仅支持中文、英文、数字，20个字符内"),
    CHANGE_DEPARTMENT_ERROR_EFFECTIVE(511003, "您只能选择当前日期及之后的时间"),
    CHANGE_DEPARTMENT_ERROR_REASON(511004, "最多可输入最多800字符"),
    CHANGE_DEPARTMENT_ERROR_REMARK(511005, "最多可输入最多800字符"),
    CHANGE_DEPARTMENT_ERROR_APPENDIX(511006, "附件总数量不能超过10个"),
    CHANGE_DEPARTMENT_ERROR_COMPANY(511007, "申请人公司信息错误"),
    CHANGE_DEPARTMENT_ERROR_DEPARTMENT(511008, "申请人部门信息错误"),
    CHANGE_DEPARTMENT_ERROR_APPLY_AUTH(511009, "没有权限发起"),
    COMMISSAR_MEMBER_IS_EMPTY(511010,"部门对应政委人员为空"),
    COMMISSAR_IS_NULL(511011,"缺少政委审批组"),
    CHANGE_DEPARTMENT_ERROR_EDIT_EXIST(511012, "已修改过岗位,修改失败"),
    CHANGE_DEPARTMENT_ERROR_SAME_DEPART(511013, "部门相同，调整失败"),
    CHANGE_DEPARTMENT_ERROR_SAME_POSITION(511014, "岗位相同，调整失败"),
    CHANGE_DEPARTMENT_ERROR_EDIT_AUTH(511015, "没有权限修改"),
    CHANGE_DEPARTMENT_ERROR_EDIT(511016, "数据不存在,修改失败"),
    CHANGE_DEPARTMENT_ERROR_NOTIFY(509017, "发送通知失败"),
    CHANGE_DEPARTMENT_ERROR_LEADER(509018, "当前员工是部门领导，无法调整"),
    CHANGE_DEPARTMENT_ERROR_ING(509019, "已进入审批流程中，无法撤销"),
    CHANGE_DEPARTMENT_ERROR_MAINNULL(511017,"请选择主部门"),
    CHANGE_DEPARTMENT_ERROR_MAINONE(511018,"主部门只可以选择一个"),
    IS_NOT_COMMISSAR(511019, "该员工不是政委，请选择政委"),
    CHANGE_DEPARTMENT_ERROR_EXIST(511019, "您当前员工有一个审批流尚未结束，请先完成上一个审批流。"),
    APPROVE_IS_NULL(511021, "没有对应的审批组"),
    TRANSFER_APPLY_DELETE_FAIL(511022, "员工调岗申请撤销失败"),
    IS_NOT_HRBP(511023, "此人不是绩效HRBP"),
    ILLEGAL_TRANSFER_DEPARTMENT_FORM(511024, "不合法的调岗形式"),
    SELF_TRANSFER_DEPARTMENT_ERROR(511025, "发起人不能对自己发起调岗"),
    CANNOT_TRANSFER_DEPARTMENT_INTO_TOP(511026, "调整部门不允许为哈罗出行！"), // 调岗暂不能调入顶级部门
    CURR_TRANSFER_POSITION_SALARY_ERROR(511027, "本部门调岗仅调薪请选择薪资调整模块"),
    ILLEGAL_TRANSFER_DEPARTMENT_TYPE(511028, "不合法的调岗类型"),

    // 512:审批组
    FLOW_ROLE_DOUBLE(512001,"审批组内已有该员工"),
    FlOW_ROLE_NULL(512001,"审批组没有对应审批人员或管理范围不包括员工所属部门"),
    FLOW_ROLE_EMPTY(512002,"审批组没有对应审批人员或管理范围不包括员工所属部门"),

    // 513: 离职相关
    DIMISSION_ERROR_USER(513001, "员工信息错误"),
    DIMISSION_ERROR_ENTRY(513002, "员工入职时间不存在"),
    DIMISSION_ERROR_REASON(513003, "至少选择一项离职原因"),
    DIMISSION_ERROR_APPENDIX(513004, "附件总数量不能超过5个"),
    DIMISSION_INFO_NULL(513005, "离职信息为空"),
    DIMISSION_ERROR_HANDOVER(513006, "交接人信息错误"),
    DIMISSION_ERROR_COUNTERSIGN(513007, "会签组成员应不少于5人"),
    DIMISSION_ERROR_DEPART(513008, "获取申请人部门信息失败"),
    DIMISSION_ERROR_APPROVE(513009, "获取申请人审批列表信息失败"),
    DIMISSION_ERROR_COMMISSAR(513010, "获取申请人政委信息失败"),
    DIMISSION_ERROR_ING(513011, "已进入审批流程中，无法撤销"),
    DIMISSION_ERROR_STATUS(513012, "员工已经是待离职或离职状态"),
    DIMISSION_ERROR_OPT_ING(513013, "操作失败，员工离职审批中"),
    DIMISSION_ERROR_EXIST(513014, "您当前员工有一个审批流尚未结束，请先完成上一个审批流。"),
    DIMISSION_ERROR_COMMISSAR_DEPART(513015, "未获取到政委主部门信息"),
    DIMISSION_ERROR_CONFIRM(513016, "操作失败，请先在钉钉中完成离职操作"),
    DIMISSION_ERROR_DEL_TURN(513017, "操作失败，不能删除转正时间"),
    DIMISSION_ERROR_DEL_ENTRY(513018, "操作失败，不能删除入职时间"),
    DIMISSION_ERROR_COUNTER_INFO(513019, "获取会签组人员信息失败"),
    DIMISSION_ERROR_ROLE_CENTER(513020, "获取中心负责人信息失败"),
    DIMISSION_ERROR_ROLE_COMM(513021, "获取政委上级领导信息失败"),
    DIMISSION_ERROR_ROLE_MAN(513022, "操作失败，该员工为主管或审批人"),
    DIMISSION_NOT_EXIST_MAIN_DEPART(513023, "政委或员工未设置主部门，请联系HRBP"),
    DIMISSION_CEO_ERROR(513024, "不可对CEO进行此操作"),
    DIMISSION_ERROR_HANDOVER_EMPLOYEEID(513025, "交接人不能为离职申请人"),
    DIMISSION_TYPE_ILLEGAL(513026, "不合法的离职类型"),
    DIMISSION_FUNCTIONAL_DEPART_NOT_EXIST(513027, "职能中心部门信息不存在"),
    FUNCTIONAL_DEPART_LEADER_NOT_EXIST(513028, "职能中心部门主管不存在"),
    DIMISSION_ERROR_COMMISSAR_EMPLOYEEID(513029,"政委不能进行辞退本人操作"),
    ERROR_INFORMATION_SECURITY_DEPARTMENT(513030,"获取信息安全部成员失败"),
    DIMISSION_RESIGN_EXIST(513031,"该员工已有离职申请/代离职申请/辞退申请单，不可手动操作"),
    COUNTERSIGN_REJECT_AUTH_FAILED(513032,"会签组拒绝权限校验失败"),

    // 514 销假
    ORIGIN_VACATION_APPLY_REJECT_OR_DELETE(514000, "请假申请已经拒绝或撤销"),
    ALREADY_REVOKE_LEAVE_APPLY(514001, "请假申请已经撤销"),
    VACATION_REVOKE_CHECK_FAILED(514002, "销假申请校验失败"),
    VACATION_END_DATE_TOO_FAR(514003, "原假期结束时间距现在超出1个月"),
    NOT_REVOKE_VACATION_APPLY(514004, "非销假申请"),
    GET_ORIGIN_ACTIVITI_PARAMS_FAILED(514005, "获取原请假申请流程参数失败"),
    REVOKE_LEAVE_APPLY_NOT_EXIST(514006, "销假申请不存在"),
    NOT_LEAVE_APPLY_USER(514007, "非原请假申请人"),
    REVOKE_LEAVE_APPLY_PROCESSING(514008, "请假撤销已经在处理中"),


    //601 政府杂项相关异常
    GOVERNMENT_ERROR_USERNAME(601001,"汇报人姓名不能为空"),
    GOVERNMENT_ERROR_PROVINCE(601002,"汇报省份不能为空"),
    GOVERNMENT_ERROR_TITLE(601003,"概述不能超过40字"),
    GOVERNMENT_ERROR_CONTENT(601004,"内容不能超过1000字"),
    GOVERNMENT_ERROR_NONOTITLE(601003,"概述不能为空"),
    GOVERNMENT_ERROR_NONECONTENT(601004,"内容不能为空"),
    GOVERNMENT_ERROR_COMMENT(601005,"评论内容不能超过500字"),
    GOVERNMENT_ERROR_NOAUTH(601006,"您没有权限!"),
    GOVERNMENT_ERROR_NONPHONE(601007,"手机号与EHR注册不一致"),
    GOVERNMENT_ERROR_CODETIMES(601007,"超限!验证码每小时只能获取5次"),
    GOVERNMENT_ERROR_EXPIRE(601008,"验证码已经超时,请重新获取"),
    GOVERNMENT_ERROR_WRONG(601009,"验证码验证错误,请重新输入验证码"),

    // 514: 调薪调职级
    CHANGE_SALARY_ERROR_USER(514001, "员工信息错误"),
    CHANGE_SALARY_ERROR_RANK(514002, "员工新职级信息错误"),
    CHANGE_SALARY_ERROR_APPENDIX(514003, "附件总数量不能超过10个"),
    CHANGE_SALARY_ERROR_APPROVE(514004, "获取申请人审批列表信息失败"),
    CHANGE_SALARY_ERROR_SAME(514005, "请检查输入的调整内容，不能与调整前一致。"),
    CHANGE_SALARY_ERROR_EXIST(514006, "您当前员工有一个审批流尚未结束，请先完成上一个审批流。"),
    COMMISSAR_OWN_DEPART_NULL(514007, "政委管理部门为空"),


    // 515: 差旅报销
    CITY_NOT_FOUND(515001, "未找到该城市名"),
    BANK_CARD_CHANGE_FAIL(515002, "银行卡变更操作失败"),
    TRIP_APPLY_CHECK_FAIL(515003, "该出差申请单已报销"),
    TRIP_SUBSIDY_ERROR(515004, "计算出差补贴金额失败"),
    TRIP_REIMBURSE_SUBMIT_ERROR(515005, "差旅报销提交申请失败"),
    TRIP_REIMBURSE_ACCOUNT_ERROR(515006, "差旅报销入账失败"),
    TRIP_REIMBURSE_ACCOUNT_FILE_ERROR(515007, "差旅报销入账EXCEL解析失败"),
    TRIP_REIMBURSE_ACCOUNT_FILE_CLOSE_ERROR(515008, "差旅报销入账文件关闭失败"),
    TRIP_REIMBURSE_ACCOUNT_FILE_NULL_ERROR(515009, "差旅报销入账EXCEL解析结果为空"),
    TRIP_REIMBURSE_WORKFLOW_ERROR(515010, "流程查询为空"),
    FAILED_TO_DELETE_TRIP_REIMBURSE(515011,"差旅报销申请单删除处理失败"),
    FAILED_TO_BUILD_TRIP_REIMBURSE(515012,"差旅报销申请单上传失败"),
    TRIP_REIMBURSE_CALCULATE_ERROR(515013, "差旅报销申请单金额核算失败"),
    TRIP_REIMBURSE_SAME_INVOICE_ERROR(515014, "所填发票代码与发票号码重复"),


    //516：背调接口异常
    BACKGROUND_SUCCESS(516000, "成功"),
    REALNAME_AUTHENTICATION_FAIL(516001, "实名认证接口调用失败"),
    POPULATION_MODEL_FAIL(516002, "重点人口模型接口调用失败"),
    CREDIT_RISK_INDEX_FAIL(516003, "信用风险指数接口调用失败"),
    COURT_NOTICE_FAIL(516004, "法院公告信息接口调用失败"),
    CREDIT_DISHONESTY_FAIL(516005, "失信接口调用失败"),
    BACKGROUND_NON_CONFORMITY(516006, "背调不符合条件"),
    BACKGROUND_IS_EXSIT(516007, "背调已经信息已经存在"),
    REALNAME_DATA_DIFFER(516008, "姓名与身份证件不一致"),
    POPULATION_DATA_ERROR(516009, "失败：数据源异常"),
    ID_NUMBER_ERROR(516009, "身份证号码错误"),
    CREATE_EXCEL_ERROR(516010, "生成Excle失败"),
    UPLOAD_EXCEL_IS_NULL(516011, "上传内容为空"),
    UPLOAD_EXCEL_OVER_1000(516012, "上传数据超过1000"),
    PARSING_EXCEL_FAIL(516013, "解析Excle失败"),
    PLEASE_DOWN_TEMPLATE(516014, "请下载模版上传"),
    UPLOAD_VERSION_NOT_MATCH_PLEASE_DOWN_TEMPLATE(516014, "上传文件版本不匹配，请下载模版进行上传"),


    //517：二维码
    QR_TYPE_NOT_EXIST(517001, "该二维码类型不存在"),
    QR_HAVING_BANNED(517002, "该二维码已被禁用"),
    QR_OVER_USE_TIME(517003, "该二维码超出当日使用次数"),

    //518:实习生转正相关
    INTERN_APPLY_RIGHT(518001, "您暂无权限"),
    INTERN_APPLY_NULL(518002, "暂无转正实习生"),
    INTERN_START_NULL(518003, "实习开始时间为空"),
    INTERN_END_NULL(518004, "实习结束时间为空"),
    INTERN_GRADUATE_NULL(518005, "毕业时间为空"),
    SALARY_ROLE_NULL(518006,"缺少薪酬专员审批组"),
    SALARY_MEMBER_NULL(518007,"暂无薪酬专员"),
    SALARY_MANAGE_ROLE_NULL(518008,"缺少员工关系主管审批组"),
    SALARY_MANAGE_MEMBER_NULL(518009,"缺少员工关系主管"),
    CONTRACT_ROLE_NULL(518010,"缺少合同专员审批组"),
    CONTRACT_MEMBER_NULL(518011,"暂无合同专员"),
    COMPLETE_TASK_EXCEPTION(518012,"实习生审批流异常"),
    INTERN_SALARY_FAIL(518013,"实习生绩效薪资提交失败"),
    INTERN_APPLY_DELETE_FAIL(518014, "撤销实习生转正申请状态更改失败"),
    INTERN_APPLY_UPDATE_STATUS(518015, "更改实习生转正申请状态失败"),
    INTERN_TRANSFER_INSERT_FAIL(518016, "添加实习生转正调薪调岗信息失败"),
    INTERN_DELETE_ERROR(518017, "已进入审批流程中，无法撤销"),
    INTERN_STATUS_UPDATE_FAIL(518018, "修改实习生转正申请状态失败"),
    INTERN_NOTICE_FAIL(518019, "通知消息发送失败"),
    INTERN_LEADER_NULL(518020, "通知消息发送失败"),

    //519：调岗调薪调职级
    SALARY_TRANSFER_TYPE_NULL(519001,"调薪类型为空"),
    SALARY_TRANSFER_REASON_NULL(519002,"调薪原因为空"),
    VALID_DATE_IS_NULL(519003,"生效日期为空"),
    RANK_TRANSFER_TYPE_NULL(519004,"调职级类型为空"),
    RANK_TRANSFER_REASON_NULL(519005,"调职级原因为空"),
    RANK_ID_IS_NULL(519006,"新职级原ID为空"),
    SALARY_TRANSFER_TASK_ID_NULL(519007,"调薪流程任务ID为空"),
    SALARY_TRANSFER_INSTANCE_ID_NULL(519008,"调薪BPMN流程ID为空"),
    SALARY_TRANSFER_APPLY_ID_NULL(519009,"调薪申请ID为空"),
    RANK_TRANSFER_TASK_ID_NULL(519010,"调职级流程任务ID为空"),
    RANK_TRANSFER_INSTANCE_ID_NULL(519011,"调职级BPMN流程ID为空"),
    RANK_TRANSFER_APPLY_ID_NULL(519012,"调职级申请ID为空"),
    SALARY_INFO_NULL(519013,"薪资信息为空"),
    SALARY_INFO_UNCHANGED(519013,"薪资信息未变动"),
    SALARY_PERFORMANCE_NULL(519014,"绩效薪资为空"),
    PERFORMANCE_MAX_NULL(519015,"最高绩效系数为空"),
    PERFORMANCE_MIN_NULL(519016,"最低绩效系数为空"),
    REJECT_REASON_NULL(519017,"拒绝原因为空"),
    COMPLETE_OUTCOME_NULL(519018,"审批类型为空"),
    SALARY_WORKFLOW_EXIST(519019,"此员工正在调薪资审批流程中"),
    RANK_WORKFLOW_EXIST(519020,"此员工正在调职级审批流程中"),
    SALARY_TRANSFER_NOT_PERMIT(519021,"不允许薪资调整"),
    REJECT_REASON_LONG(519022,"拒绝原因过长"),
    SALARY_TRANSFER_REASON_LONG(519023,"调薪原因过长"),
    RANK_TRANSFER_REASON_LONG(519024,"调职级原因过长"),

    //520：群组
    GROUP_NAME_NULL(520001, "群组名为空"),
    GROUP_BUSINESS_TAG_NULL(520002, "群组业务标志为空"),
    GROUP_MEMBER_NULL(520003, "群组成员为空"),
    GROUP_NAME_ILLEGAL(520004, "群组名已存在"),
    GROUP_PARENT_ILLEGAL(520005, "上级群组不合法"),
    GROUP_NOT_EXIST(520006, "群组不存在"),
    GROUP_CREATOR_NOT_EXIST(520007, "创建者不存在"),
    GROUP_MANAGER_NOT_EXIST(520008, "管理者不存在"),
    GROUP_BUSINESS_KEY_NOT_EXIST(520009, "businessKey不存在"),
    GROUP_BUSINESS_KEY_AUTH_FAILED(5200010, "没权限操作此群组"),
    GROUP_CAN_NOT_DELETE(5200011, "存在下级群组，不能删除"),
    GROUP_PARENT_CAN_NOT_SELF(5200012, "当前群组不能作为上级群组"),
    GROUP_PARENT_BUSINESS_NOT_MATCH(5200013, "仅可选择同一业务标志的群组作为上级群组"),




    //530：派遣相关
    DISPATCH_ID_IS_NULL(530001,"派遣ID为空"),
    DISPATCH_INSTANCE_ID_NULL(530002,"BPMN流程ID不能为空"),
    DISPATCH_NOT_EXIST(530003,"派遣信息不存在"),
    DISPATCH_APPLY_USER_ID_NULL(530004,"派遣申请人ID为空"),
    DISPATCH_EMPLOYEE_ID_NULL(530005,"派遣员工ID为空"),
    DISPATCH_HAS_INVALID(530006,"此员工有未生效调岗申请,不可申请派遣"),
    TRANSFER_HAS_INVALID(530007,"此员工有待审批or进行中派遣单,不可申请调岗"),
    DISPATCH_APPROVE_LIST_NOT_EXIT(530008,"审批人列表为空"),
    DISPATCH_REPLACE_EMPLOYEE_NOT_EXIT(530009,"转签审批人信息获取失败"),
    DISPATCH_NOT_YOURSELF(530010,"发起人不可对自己发起派遣流程"),
    DISPATCH_TYPE_IS_NULL(530011,"请选择派遣类型"),
    CHECK_DISPATCH_1(530012,"此员工没有派遣流程单记录,请选择首次派遣"),
    CHECK_DISPATCH_2(530013,"此员工最近一条派遣单为派遣留用单,不可选择派遣留用/延长"),
    CHECK_DISPATCH_3(530014,"此员工最近一条派遣遣留用单已生效,不可选择派遣返回"),
    CHECK_DISPATCH_4(530015,"此员工最近一条派遣单为派遣返回单,请选择首次派遣"),
    CHECK_DISPATCH_5(530016,"此员工最近一条派遣单审批流程未生效,不能再次申请派遣单"),
    CHECK_DISPATCH_6(530017,"被派遣人员工类型:全职,员工状态:试用/正式,除此以外其他人员不可被派遣"),
    CHECK_DISPATCH_7(530018,"派遣地不能为常驻地、入职地"),
    CHECK_DISPATCH_8(530019,"该员工三个月内有相同派遣地派遣流程,请选择派遣延长"),
    CHECK_DISPATCH_9(530020,"请选择派遣返回"),
    CHECK_DISPATCH_10(530021,"派遣部门不能是员工主部门"),
    CHECK_DISPATCH_11(530022,"派遣延长部门只能是员工当前所在主部门"),
    CHECK_DISPATCH_12(530023,"派遣返回部门不能是员工当前所在主部门"),
    CHECK_DISPATCH_13(530024,"哈啰出行，无上级主管"),
    CHECK_DISPATCH_14(530025,"无法获取审批人，请联系HR。"),
    DISPATCH_MODIFY_ASSIGNEE_REJECT(530026,"员工本人确认不允许转签/替换"),

    // 600 假期管理
    MODIFY_NUM_LIMIT(600001, "修改数据数量限制"),
    EMPLOYEE_VACATION_INFO_NOT_EXIST(600002, "员工年假数据不存在"),
    VACATION_APPLY_NOT_REJECT_OR_DELETE(600003, "请假申请不是拒绝或撤销状态"),
    VACATION_TYPE_NOT_NEED_FEEDBACK(600004, "请假类型不需要回写"),
    VACATION_APPLY_NOT_EXIST(600005, "请假明细不存在"),
    NOT_LEAVE_APPLY(600006, "非请假申请"),
    VACATION_APPLY_HAS_BACK_WRITE(600007, "请假额度已经回补"),
    EMPLOYEE_VACATION_BACK_WRITE_FAILED(600008, "请假额度回补失败"),
    VACATION_MODIFY_REASON_NOT_BLANK(600009, "假期额度修改原因不能为空"),
    ANNUAL_BALANCE_NOT_EXCESS(600010,"法定年假和福利年假总额度不能超过15天"),
    MODIFY_BALANCE_EXCESS(600011,"修改额度不能大于固定额度"),
    LEGAL_BALANCE_NOT_MODIFY(600012,"法定额度不能手动修改"),
    AT_LEAST_ONE_EXPORT_HEADER(600013,"至少选择一项表头"),
    VACATION_YEAR_ERROR(600014,"请假年份错误"),
    VACATION_DATA_ERROR(600015,"假期详情错误"),
    VACATION_BOUND_ERROR(600016,"假期边界校验错误"),
    VACATION_REPAIR_FAILED(600017,"假期数据修复失败"),
    VACATION_DATA_NOT_MATCH(600018,"假期数据不匹配"),
    VACATION_DATA_TYPE_NOT_MATCH(600019,"假期数据请假类型不匹配"),
    VACATION_DATA_DAYS_NOT_MATCH(600020,"假期数据请假时长不匹配"),
    VACATION_WRITE_BACK_ERROR(600021,"假期数据回补错误"),

    // 602每刻业务相关
    GET_SSO_AUTH_TOKEN_FAILED(602000, "获取每刻AuthToken失败，请联系财务人员检查账号同步"),


    // 8xx：安全异常码
    SECURITY_USE_ERROR(800001, "加密使用异常"),
    SECURITY_DATA_ERROR(800002, "加密数据异常"),
    SECURITY_LENGTH_ERROR(800003, "加密长度异常"),

    // 9xx: 其它异常
    ES_ENGINE_IO_EXCEPTION(900001, "es引擎调用异常"),
    FILE_TO_STREAM_EXCEPTION(900002, "文件转为输出流异常"),
    SEND_TO_OSS_EXCEPTION(900003,"文件上传至oss异常"),
    EXPORT_TO_EXCEL_EMPTY(900004,"导出数据为空"),
    EXPORT_TO_EXCEL_ERROR(900005,"导出数据失败"),
    SALARY_ENGINE_IO_EXCEPTION(900001, "薪酬系统调用异常,请联系管理员"),
    SALARY_ENGINE_TYPE_EXCEPTION(900002, "上传出错!,请检查文件内容"),
    SALARY_UPLOAD_TYPE_EXCEPTION(900006, "请校验工号姓名"),
    SALARY_UPLOADUP_TYPE_EXCEPTION(900007, "导入数据少于系统数据"),
    SALARY_UPLOADDOWN_TYPE_EXCEPTION(900008, "导入数据多于系统数据"),
    SALARY_UPLOADUP_OTHER_EXCEPTION(900009, "解析文件异常,请检查文件内容"),
    ADJUSTMENT_SALARY_NULLPOINT_EXCEPTION(900010, "上传数据存在不属于EHR的用户请检查数据"),
    ADJUSTMENT_SALARY_CONFIRM_EXCEPTION(900011, "数据已被确认不能删除"),
    NO_MATCH_JOBCODE(900012,"没有匹配的jobCode"),
    NO_CALENDER_INFO(900013, "选择日期距离现在太久，系统无法查询到那一天是否为工作日"),
    CLONE_FAILED(900014, "对象克隆异常"),
    FINDHRFAILBYMOKAINFO(900015,"根据moka信息未能找到匹配的HR"),
    DISMISSION_EMPLOYMENT(900016,"%s 已离职"),
    PLEASE_CHOOSE_CANDIDATE(900017, "请选择候选人"),
    INVALID_COLOR_SIGN(900018, "无效的颜色标记类型"),
    CITY_INFO_SYNC_ERROR(900019,"携程城市信息同步错误异常"),
    EMPLOYEE_INFO_SYNC_ERROR(900020,"携程人事信息同步错误异常"),
    EMPLOYEE_INFO_FULL_SYNC_ERROR(900021,"携程人事全量信息同步错误异常"),
    EMAIL_MULTIPLE_RECORDS(900022, "根据邮箱匹配到多条员工信息记录"),
    WORKFLOW_EXPORT_NOT_SUPPORT(90000523,"流程导出类型暂不支持"),

    CREATE_QUESTIONNAIRECONFIG_PARAMTER_LOSE(600053,"创建问卷参数缺失"),
    UPLOAD_FILE_OUT_OF_TIME(600054,"上传文件超时"),
    UPADTE_QUESTIONNAIRE_ENABLE_PARAMTER_LOSE(600055,"更新问卷可用状态参数缺失"),
    UPADTE_QUESTIONNAIRECONFIG_PARAMTER_LOSE(600056,"更新问卷参数缺失"),
    EXCEL_PARAMETER_ERROR(600057,"导入excel数据错误"),
    QUESTIONNAIRE_SEND_MESSAGE_PARAMTER_LOSE(600058,"问卷消息发送参数缺失"),
    QUESTIONNAIRE_EMPLOYEE_INFO_ALL_ERROR(600059,"调研人员信息全部不符"),
    QUESTIONNAIRE_EMPLOYEE_IFNO_ERROR(600060,"%s 人员信息不符"),
    QUESTIONNAIRE_NOT_EXIST(600061,"问卷不存在"),
    QUESTIONNAIRE_SUBMIT_PARAMATER_ERROR(600062,"问卷提交参数有误"),
    QUESTIONNAIRE_IS_OVERDUE(600063,"问卷已过期"),
    QUESTIONNAIRE_EMPLOYEE_NOT_EXIST(600064,"当前用户不属于被调研人员"),
    QUESTIONNAIRE_PARAMETER_IS_ILLEGAL(600065,"问卷参数不合法"),
    QUESTIONNAIRE_NAME_IS_EXIST(600066,"问卷名称已存在"),
    QUESTIONNAIRE_HAS_BEEN_FINISHED(600067,"问卷已填写"),
    QUESTIONNAIRE_IS_UNENABLE(600067,"问卷不可用"),

    RANK_LIST_NOT_NULL(1000001,"职级序列不能为空"),
    DESCRIPTION_TOO_LONG(1000002,"序列说明超过500字符"),
    REMARK_TOO_LONG(1000003,"备注超过500字符"),
    RANK_NOT_EXIST(1000004,"职级不存在，请刷新重试"),
    RANK_EXIST(1000005,"职级序列已经存在，请换一个试试"),
    RANK_IS_USED(1000006,"职级正在使用，无法删除"),
    RANK_FORMAT_ERROR(1000007,"职级序列名称不正确，只能含有字母"),
    RANK_RANGE_ERROR(1000008,"职级范围不正确"),
    RANK_NAME_ERROR(1000009,"级别格式不正确"),
    RANK_NAME_EXIST(1000010,"级别已经存在"),
    POSITION_NAME_EXIST(1000011,"已存在相同名字基项"),
    POSITION_NAME_CAN_NOT_DELETE(1000012,"删除失败，该基项已构成职位序列"),
    LIST_ID_SIZE(1000013,"职位序列参数错误"),
    POSITION_LIST_EXIST(1000014,"此职位序列已存在，请修改"),
    POSITION_LIST_BASE_EXIST(1000015,"基准岗位名称已被更新，请刷新后重新生成职位序列"),
    POSITION_LIST_RANK_EXIST(1000016,"该职级职位序列绑定人数大于0"),
    POSITION_LIST_ALREADY_DELETED(1000017,"该职位序列已停用"),
    MEIKE_SSO_ERROR(1000018,"无匹配账号，请联系管理员"),
    YONGYOU_SIGN_ERROR(1000019,"无法获取加签URL，请联系管理员"),
    YONGYOU_APPROVAL_ERROR(1000020,"获取用友审批数据异常"),
    BEISEN_USER_ID_IS_NULL(1000022,"您没有北森用户ID，请联系管理员"),
    BEISEN_PENDING_TASK_ERROR(1000023,"获取北森待审批任务异常"),
    BEISEN_APPROVAL_TASK_ERROR(1000024,"获取北森已审批任务异常"),
    BEISEN_APPLY_TASK_ERROR(1000025,"获取北森用户发起任务异常"),
    BEISEN_PROCESS_TYPE_ERROR(1000026,"获取北森流程类型异常"),
    BEISEN_PENDING_COUNT_ERROR(1000027,"获取北森待审批任务数异常"),
    BEISEN_MOBILE_COUNT_ERROR(1000028,"获取审批数量异常"),
    BEISEN_MOBILE_PENDING_TASK_ERROR(1000029,"获取移动端待审批任务异常"),
    BEISEN_MOBILE_APPROVED_TASK_ERROR(1000030,"获取移动端已审批任务异常"),
    BEISEN_MOBILE_APPLY_TASK_ERROR(1000031,"获取移动端已发起任务异常"),
    BEISEN_MOBILE_CC_TASK_ERROR(1000031,"获取移动端抄送我的任务异常"),
    ;

    private int code;

    private String message;

    private ExceptionEnum(int code, String message){
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
