package io.springbootstarter.exception;

public class ApplException extends RuntimeException {

    private final String code;
    private final String message;
    private static final String ERROR_CODE_10040 = "10040";

    public ApplException(String code, String message) {
        this.code=code;
        this.message=message;
    }

    public ApplException(Code code) {
        this.code = code.getCode();
        this.message = code.getMessage();
    }

    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public enum Code{
        /**
         * The {@code ERROR_OCCURRED} is thrown when an exception occurs that is not
         * expected by the system.
         *
         * <p>
         * <b>Code:</b> 10015, 10016
         * </p>
         */
        CUSTOM_ERROR("010101","%s")
        ,VALIDATION_ERROR("10000","Validation Error")
        ,INTERNAL_SERVER_ERROR("10001", "INTERNAL_SERVER_ERROR")
        ,DATE_PARSE_ERROR("10002","DATE_PARSE_ERROR")
        ,DATA_NOT_FOUND("10003","No data found")
        ,DATA_NOT_FOUND_BY_ID("10003","No data found by id: '%s'")
        ,DATA_NOT_UPDATED("10004","No data updated")
        ,DATA_NOT_UPDATED_BY_ID("10004","No data updated by id: '%s'")
        ,DATA_NOT_DELETED("10005","No data deleted")
        ,DATA_NOT_DELETED_BY_ID("10006","No data deleted by id: '%s'")
        ,DATA_NOT_INSERTED("10007","No data inserted")
        ,DATA_NOT_INSERTED_BY_ID("10008","No data inserted by id: '%s")
        ,EXCEL_ERROR("10009","Unable to generate excel")
        ,PDF_ERROR("10010","Unable to generate pdf")
        ,IMAGE_NOT_FOUND_ERROR("10011","No image found")
        ,IMAGE_NOT_FOUND_BY_NAME_ERROR("10012","No image: '%s' found ")
        ,IMAGE_NOT_SAVED_ERROR("10013","Image not saved")
        ,FILE_UPLOAD_ERROR("10014","File(s) not uploaded")
        ,FILE_DOWNLOAD_ERROR("10015","File not downloaded")
        ,ROLE_OR_ROLE_NAME_NOT_FOUND("10016","Role or role name not found.")
        ,NO_MENUS_CONFIGURED("10017","No menus are configured.")
        ,EMPTY_PAYLOAD("10018","Payload is missing or empty")
        ,DUPLICATE_DATA("10019","Duplicate record in Database")
        ,INVALID_EMAIL("10020","Invalid email id")
        ,INVALID_UPN("10021","Invalid upn")
         ;



        private final String code;
        private final String message;

        /**
         * Internal constructor to define type properties.
         *
         * @param code Unique DataAccessExceptionCode representing exception type.
         */
        Code(final String code, final String message) {
            this.code = code;
            this.message = message;
        }

        public String getCode() {
            return this.code;
        }
        public String getMessage() {
            return this.message;
        }
    }
}
