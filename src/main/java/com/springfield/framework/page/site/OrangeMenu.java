package com.springfield.framework.page.site;

public enum OrangeMenu {

    LOGIN("/auth/login"),

    USR_MANAGEMENT("/admin/viewSystemUsers"),
    JOB_TITLES("/admin/viewJobTitleList"), PAY_GRADES("/admin/viewPayGrades"), EMPLOYEE_STATUS("/admin/employmentStatus"), JOB_CATEGORY("/admin/jobCategory"), WORK_SHIFT("/admin/workShift"),
    GENERAL_INFO("/admin/viewOrganizationGeneralInformation"), LOCATIONS("/admin/viewLocations"), STRUCTURE("/admin/viewCompanyStructure"),
    SKILLS("/admin/viewSkills"), EDUCATION("/admin/viewEducation"), LICENSES("/admin/viewLicenses"), LANGUAGES("/admin/viewLanguages"), MEMBERSHIPS("/admin/membership"),
    NATIONALITIES("/admin/nationality"), CORPORATE_BRANDING("/admin/addTheme"),
    EMAIL_CONFIGURATION("/admin/listMailConfiguration"), EMAIL_SUBSCRIPTION("/admin/viewEmailNotification"), LOCALIZATION("/admin/localization"), LANGUAGE_PACKAGES("/admin/languagePackage"), MODULES("/admin/viewModules"), SOCIAL_MEDIA_AUTH("/admin/openIdProvider"), REGISTER_OAUTH_CLIENT("/admin/registerOAuthClient"),

    ADD_EMPLOYEE("/pim/addEmployee");

    private final String strValue;

    public String getStrValue() {
        return this.strValue;
    }

    OrangeMenu(String option) {
        this.strValue = option;
    }
}
