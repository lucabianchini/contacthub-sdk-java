
package it.contactlab.hub.sdk.java.models.base;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class BaseProperties {

    /**
     * the picture url of customer
     * 
     */
    @SerializedName("pictureUrl")
    @Expose
    private URI pictureUrl;
    /**
     * the title
     * 
     */
    @SerializedName("title")
    @Expose
    private String title;
    /**
     * the prefix
     * 
     */
    @SerializedName("prefix")
    @Expose
    private String prefix;
    /**
     * the first name
     * 
     */
    @SerializedName("firstName")
    @Expose
    private String firstName;
    /**
     * the last name
     * 
     */
    @SerializedName("lastName")
    @Expose
    private String lastName;
    /**
     * the middle name
     * 
     */
    @SerializedName("middleName")
    @Expose
    private String middleName;
    /**
     * gender
     * 
     */
    @SerializedName("gender")
    @Expose
    private String gender;
    /**
     * date of birth
     * 
     */
    @SerializedName("dob")
    @Expose
    private String dob;
    /**
     * the locale
     * 
     */
    @SerializedName("locale")
    @Expose
    private String locale;
    /**
     * the time zone
     * 
     */
    @SerializedName("timezone")
    @Expose
    private BaseProperties.Timezone timezone;
    /**
     * the contact information
     * 
     */
    @SerializedName("contacts")
    @Expose
    private Contacts contacts;
    /**
     * the address information
     * 
     */
    @SerializedName("address")
    @Expose
    private Address address;
    /**
     * credential
     * 
     */
    @SerializedName("credential")
    @Expose
    private Credential credential;
    /**
     * educations
     * 
     */
    @SerializedName("educations")
    @Expose
    private List<Education> educations = new ArrayList<Education>();
    /**
     * likes
     * 
     */
    @SerializedName("likes")
    @Expose
    private List<Like> likes = new ArrayList<Like>();
    /**
     * Social profile
     * 
     */
    @SerializedName("socialProfile")
    @Expose
    private SocialProfile socialProfile;
    /**
     * jobs
     * 
     */
    @SerializedName("jobs")
    @Expose
    private List<Job> jobs = new ArrayList<Job>();
    /**
     * subscriptions
     * 
     */
    @SerializedName("subscriptions")
    @Expose
    private List<Subscription> subscriptions = new ArrayList<Subscription>();

    /**
     * the picture url of customer
     * 
     * @return
     *     The pictureUrl
     */
    public URI getPictureUrl() {
        return pictureUrl;
    }

    /**
     * the picture url of customer
     * 
     * @param pictureUrl
     *     The pictureUrl
     */
    public void setPictureUrl(URI pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    /**
     * the title
     * 
     * @return
     *     The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * the title
     * 
     * @param title
     *     The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * the prefix
     * 
     * @return
     *     The prefix
     */
    public String getPrefix() {
        return prefix;
    }

    /**
     * the prefix
     * 
     * @param prefix
     *     The prefix
     */
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    /**
     * the first name
     * 
     * @return
     *     The firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * the first name
     * 
     * @param firstName
     *     The firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * the last name
     * 
     * @return
     *     The lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * the last name
     * 
     * @param lastName
     *     The lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * the middle name
     * 
     * @return
     *     The middleName
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     * the middle name
     * 
     * @param middleName
     *     The middleName
     */
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    /**
     * gender
     * 
     * @return
     *     The gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * gender
     * 
     * @param gender
     *     The gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * date of birth
     * 
     * @return
     *     The dob
     */
    public String getDob() {
        return dob;
    }

    /**
     * date of birth
     * 
     * @param dob
     *     The dob
     */
    public void setDob(String dob) {
        this.dob = dob;
    }

    /**
     * the locale
     * 
     * @return
     *     The locale
     */
    public String getLocale() {
        return locale;
    }

    /**
     * the locale
     * 
     * @param locale
     *     The locale
     */
    public void setLocale(String locale) {
        this.locale = locale;
    }

    /**
     * the time zone
     * 
     * @return
     *     The timezone
     */
    public BaseProperties.Timezone getTimezone() {
        return timezone;
    }

    /**
     * the time zone
     * 
     * @param timezone
     *     The timezone
     */
    public void setTimezone(BaseProperties.Timezone timezone) {
        this.timezone = timezone;
    }

    /**
     * the contact information
     * 
     * @return
     *     The contacts
     */
    public Contacts getContacts() {
        return contacts;
    }

    /**
     * the contact information
     * 
     * @param contacts
     *     The contacts
     */
    public void setContacts(Contacts contacts) {
        this.contacts = contacts;
    }

    /**
     * the address information
     * 
     * @return
     *     The address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * the address information
     * 
     * @param address
     *     The address
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * credential
     * 
     * @return
     *     The credential
     */
    public Credential getCredential() {
        return credential;
    }

    /**
     * credential
     * 
     * @param credential
     *     The credential
     */
    public void setCredential(Credential credential) {
        this.credential = credential;
    }

    /**
     * educations
     * 
     * @return
     *     The educations
     */
    public List<Education> getEducations() {
        return educations;
    }

    /**
     * educations
     * 
     * @param educations
     *     The educations
     */
    public void setEducations(List<Education> educations) {
        this.educations = educations;
    }

    /**
     * likes
     * 
     * @return
     *     The likes
     */
    public List<Like> getLikes() {
        return likes;
    }

    /**
     * likes
     * 
     * @param likes
     *     The likes
     */
    public void setLikes(List<Like> likes) {
        this.likes = likes;
    }

    /**
     * Social profile
     * 
     * @return
     *     The socialProfile
     */
    public SocialProfile getSocialProfile() {
        return socialProfile;
    }

    /**
     * Social profile
     * 
     * @param socialProfile
     *     The socialProfile
     */
    public void setSocialProfile(SocialProfile socialProfile) {
        this.socialProfile = socialProfile;
    }

    /**
     * jobs
     * 
     * @return
     *     The jobs
     */
    public List<Job> getJobs() {
        return jobs;
    }

    /**
     * jobs
     * 
     * @param jobs
     *     The jobs
     */
    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }

    /**
     * subscriptions
     * 
     * @return
     *     The subscriptions
     */
    public List<Subscription> getSubscriptions() {
        return subscriptions;
    }

    /**
     * subscriptions
     * 
     * @param subscriptions
     *     The subscriptions
     */
    public void setSubscriptions(List<Subscription> subscriptions) {
        this.subscriptions = subscriptions;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(pictureUrl).append(title).append(prefix).append(firstName).append(lastName).append(middleName).append(gender).append(dob).append(locale).append(timezone).append(contacts).append(address).append(credential).append(educations).append(likes).append(socialProfile).append(jobs).append(subscriptions).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof BaseProperties) == false) {
            return false;
        }
        BaseProperties rhs = ((BaseProperties) other);
        return new EqualsBuilder().append(pictureUrl, rhs.pictureUrl).append(title, rhs.title).append(prefix, rhs.prefix).append(firstName, rhs.firstName).append(lastName, rhs.lastName).append(middleName, rhs.middleName).append(gender, rhs.gender).append(dob, rhs.dob).append(locale, rhs.locale).append(timezone, rhs.timezone).append(contacts, rhs.contacts).append(address, rhs.address).append(credential, rhs.credential).append(educations, rhs.educations).append(likes, rhs.likes).append(socialProfile, rhs.socialProfile).append(jobs, rhs.jobs).append(subscriptions, rhs.subscriptions).isEquals();
    }

    public enum Timezone {

        @SerializedName("Acre Time")
        ACRE_TIME("Acre Time"),
        @SerializedName("Afghanistan Time")
        AFGHANISTAN_TIME("Afghanistan Time"),
        @SerializedName("Alaska Standard Time")
        ALASKA_STANDARD_TIME("Alaska Standard Time"),
        @SerializedName("Alma-Ata Time")
        ALMA_ATA_TIME("Alma-Ata Time"),
        @SerializedName("Amazon Time")
        AMAZON_TIME("Amazon Time"),
        @SerializedName("Anadyr Time")
        ANADYR_TIME("Anadyr Time"),
        @SerializedName("Aqtau Time")
        AQTAU_TIME("Aqtau Time"),
        @SerializedName("Aqtobe Time")
        AQTOBE_TIME("Aqtobe Time"),
        @SerializedName("Arabia Standard Time")
        ARABIA_STANDARD_TIME("Arabia Standard Time"),
        @SerializedName("Argentine Time")
        ARGENTINE_TIME("Argentine Time"),
        @SerializedName("Armenia Time")
        ARMENIA_TIME("Armenia Time"),
        @SerializedName("Atlantic Standard Time")
        ATLANTIC_STANDARD_TIME("Atlantic Standard Time"),
        @SerializedName("Australian Central Standard Time (Northern Territory)")
        AUSTRALIAN_CENTRAL_STANDARD_TIME_NORTHERN_TERRITORY("Australian Central Standard Time (Northern Territory)"),
        @SerializedName("Australian Central Standard Time (South Australia)")
        AUSTRALIAN_CENTRAL_STANDARD_TIME_SOUTH_AUSTRALIA("Australian Central Standard Time (South Australia)"),
        @SerializedName("Australian Central Standard Time (South Australia/New South Wales)")
        AUSTRALIAN_CENTRAL_STANDARD_TIME_SOUTH_AUSTRALIA_NEW_SOUTH_WALES("Australian Central Standard Time (South Australia/New South Wales)"),
        @SerializedName("Australian Central Western Standard Time")
        AUSTRALIAN_CENTRAL_WESTERN_STANDARD_TIME("Australian Central Western Standard Time"),
        @SerializedName("Australian Eastern Standard Time (New South Wales)")
        AUSTRALIAN_EASTERN_STANDARD_TIME_NEW_SOUTH_WALES("Australian Eastern Standard Time (New South Wales)"),
        @SerializedName("Australian Eastern Standard Time (Queensland)")
        AUSTRALIAN_EASTERN_STANDARD_TIME_QUEENSLAND("Australian Eastern Standard Time (Queensland)"),
        @SerializedName("Australian Eastern Standard Time (Tasmania)")
        AUSTRALIAN_EASTERN_STANDARD_TIME_TASMANIA("Australian Eastern Standard Time (Tasmania)"),
        @SerializedName("Australian Eastern Standard Time (Victoria)")
        AUSTRALIAN_EASTERN_STANDARD_TIME_VICTORIA("Australian Eastern Standard Time (Victoria)"),
        @SerializedName("Australian Western Standard Time")
        AUSTRALIAN_WESTERN_STANDARD_TIME("Australian Western Standard Time"),
        @SerializedName("Azerbaijan Time")
        AZERBAIJAN_TIME("Azerbaijan Time"),
        @SerializedName("Azores Time")
        AZORES_TIME("Azores Time"),
        @SerializedName("Bangladesh Time")
        BANGLADESH_TIME("Bangladesh Time"),
        @SerializedName("Bhutan Time")
        BHUTAN_TIME("Bhutan Time"),
        @SerializedName("Bolivia Time")
        BOLIVIA_TIME("Bolivia Time"),
        @SerializedName("Bougainville Standard Time")
        BOUGAINVILLE_STANDARD_TIME("Bougainville Standard Time"),
        @SerializedName("Brasilia Time")
        BRASILIA_TIME("Brasilia Time"),
        @SerializedName("Brunei Time")
        BRUNEI_TIME("Brunei Time"),
        @SerializedName("Cape Verde Time")
        CAPE_VERDE_TIME("Cape Verde Time"),
        @SerializedName("Central African Time")
        CENTRAL_AFRICAN_TIME("Central African Time"),
        @SerializedName("Central European Time")
        CENTRAL_EUROPEAN_TIME("Central European Time"),
        @SerializedName("Central Indonesia Time")
        CENTRAL_INDONESIA_TIME("Central Indonesia Time"),
        @SerializedName("Central Standard Time")
        CENTRAL_STANDARD_TIME("Central Standard Time"),
        @SerializedName("Chamorro Standard Time")
        CHAMORRO_STANDARD_TIME("Chamorro Standard Time"),
        @SerializedName("Chatham Standard Time")
        CHATHAM_STANDARD_TIME("Chatham Standard Time"),
        @SerializedName("Chile Time")
        CHILE_TIME("Chile Time"),
        @SerializedName("China Standard Time")
        CHINA_STANDARD_TIME("China Standard Time"),
        @SerializedName("Choibalsan Time")
        CHOIBALSAN_TIME("Choibalsan Time"),
        @SerializedName("Christmas Island Time")
        CHRISTMAS_ISLAND_TIME("Christmas Island Time"),
        @SerializedName("Chuuk Time")
        CHUUK_TIME("Chuuk Time"),
        @SerializedName("Cocos Islands Time")
        COCOS_ISLANDS_TIME("Cocos Islands Time"),
        @SerializedName("Colombia Time")
        COLOMBIA_TIME("Colombia Time"),
        @SerializedName("Cook Is. Time")
        COOK_IS_TIME("Cook Is. Time"),
        @SerializedName("Coordinated Universal Time")
        COORDINATED_UNIVERSAL_TIME("Coordinated Universal Time"),
        @SerializedName("Cuba Standard Time")
        CUBA_STANDARD_TIME("Cuba Standard Time"),
        @SerializedName("Davis Time")
        DAVIS_TIME("Davis Time"),
        @SerializedName("Dumont-d'Urville Time")
        DUMONT_D_URVILLE_TIME("Dumont-d'Urville Time"),
        @SerializedName("East Indonesia Time")
        EAST_INDONESIA_TIME("East Indonesia Time"),
        @SerializedName("Easter Is. Time")
        EASTER_IS_TIME("Easter Is. Time"),
        @SerializedName("Eastern African Time")
        EASTERN_AFRICAN_TIME("Eastern African Time"),
        @SerializedName("Eastern European Time")
        EASTERN_EUROPEAN_TIME("Eastern European Time"),
        @SerializedName("Eastern Greenland Time")
        EASTERN_GREENLAND_TIME("Eastern Greenland Time"),
        @SerializedName("Eastern Standard Time")
        EASTERN_STANDARD_TIME("Eastern Standard Time"),
        @SerializedName("Ecuador Time")
        ECUADOR_TIME("Ecuador Time"),
        @SerializedName("Falkland Is. Time")
        FALKLAND_IS_TIME("Falkland Is. Time"),
        @SerializedName("Fernando de Noronha Time")
        FERNANDO_DE_NORONHA_TIME("Fernando de Noronha Time"),
        @SerializedName("Fiji Time")
        FIJI_TIME("Fiji Time"),
        @SerializedName("French Guiana Time")
        FRENCH_GUIANA_TIME("French Guiana Time"),
        @SerializedName("French Southern & Antarctic Lands Time")
        FRENCH_SOUTHERN_ANTARCTIC_LANDS_TIME("French Southern & Antarctic Lands Time"),
        @SerializedName("GMT+01:00")
        GMT_01_00("GMT+01:00"),
        @SerializedName("GMT+02:00")
        GMT_02_00("GMT+02:00"),
        @SerializedName("GMT+03:00")
        GMT_03_00("GMT+03:00"),
        @SerializedName("GMT+04:00")
        GMT_04_00("GMT+04:00"),
        @SerializedName("GMT+05:00")
        GMT_05_00("GMT+05:00"),
        @SerializedName("GMT+06:00")
        GMT_06_00("GMT+06:00"),
        @SerializedName("GMT+07:00")
        GMT_07_00("GMT+07:00"),
        @SerializedName("GMT+08:00")
        GMT_08_00("GMT+08:00"),
        @SerializedName("GMT+09:00")
        GMT_09_00("GMT+09:00"),
        @SerializedName("GMT+10:00")
        GMT_10_00("GMT+10:00"),
        @SerializedName("GMT+11:00")
        GMT_11_00("GMT+11:00"),
        @SerializedName("GMT+12:00")
        GMT_12_00("GMT+12:00"),
        @SerializedName("GMT+13:00")
        GMT_13_00("GMT+13:00"),
        @SerializedName("GMT+14:00")
        GMT_14_00("GMT+14:00"),
        @SerializedName("GMT-01:00")
        GMT_01_00_("GMT-01:00"),
        @SerializedName("GMT-02:00")
        GMT_02_00_("GMT-02:00"),
        @SerializedName("GMT-03:00")
        GMT_03_00_("GMT-03:00"),
        @SerializedName("GMT-04:00")
        GMT_04_00_("GMT-04:00"),
        @SerializedName("GMT-05:00")
        GMT_05_00_("GMT-05:00"),
        @SerializedName("GMT-06:00")
        GMT_06_00_("GMT-06:00"),
        @SerializedName("GMT-07:00")
        GMT_07_00_("GMT-07:00"),
        @SerializedName("GMT-08:00")
        GMT_08_00_("GMT-08:00"),
        @SerializedName("GMT-09:00")
        GMT_09_00_("GMT-09:00"),
        @SerializedName("GMT-10:00")
        GMT_10_00_("GMT-10:00"),
        @SerializedName("GMT-11:00")
        GMT_11_00_("GMT-11:00"),
        @SerializedName("GMT-12:00")
        GMT_12_00_("GMT-12:00"),
        @SerializedName("Galapagos Time")
        GALAPAGOS_TIME("Galapagos Time"),
        @SerializedName("Gambier Time")
        GAMBIER_TIME("Gambier Time"),
        @SerializedName("Georgia Time")
        GEORGIA_TIME("Georgia Time"),
        @SerializedName("Ghana Mean Time")
        GHANA_MEAN_TIME("Ghana Mean Time"),
        @SerializedName("Gilbert Is. Time")
        GILBERT_IS_TIME("Gilbert Is. Time"),
        @SerializedName("Greenwich Mean Time")
        GREENWICH_MEAN_TIME("Greenwich Mean Time"),
        @SerializedName("Gulf Standard Time")
        GULF_STANDARD_TIME("Gulf Standard Time"),
        @SerializedName("Guyana Time")
        GUYANA_TIME("Guyana Time"),
        @SerializedName("Hawaii Standard Time")
        HAWAII_STANDARD_TIME("Hawaii Standard Time"),
        @SerializedName("Hong Kong Time")
        HONG_KONG_TIME("Hong Kong Time"),
        @SerializedName("Hovd Time")
        HOVD_TIME("Hovd Time"),
        @SerializedName("India Standard Time")
        INDIA_STANDARD_TIME("India Standard Time"),
        @SerializedName("Indian Ocean Territory Time")
        INDIAN_OCEAN_TERRITORY_TIME("Indian Ocean Territory Time"),
        @SerializedName("Indochina Time")
        INDOCHINA_TIME("Indochina Time"),
        @SerializedName("Iran Standard Time")
        IRAN_STANDARD_TIME("Iran Standard Time"),
        @SerializedName("Irkutsk Time")
        IRKUTSK_TIME("Irkutsk Time"),
        @SerializedName("Israel Standard Time")
        ISRAEL_STANDARD_TIME("Israel Standard Time"),
        @SerializedName("Japan Standard Time")
        JAPAN_STANDARD_TIME("Japan Standard Time"),
        @SerializedName("Khandyga Time")
        KHANDYGA_TIME("Khandyga Time"),
        @SerializedName("Kirgizstan Time")
        KIRGIZSTAN_TIME("Kirgizstan Time"),
        @SerializedName("Korea Standard Time")
        KOREA_STANDARD_TIME("Korea Standard Time"),
        @SerializedName("Kosrae Time")
        KOSRAE_TIME("Kosrae Time"),
        @SerializedName("Krasnoyarsk Time")
        KRASNOYARSK_TIME("Krasnoyarsk Time"),
        @SerializedName("Line Is. Time")
        LINE_IS_TIME("Line Is. Time"),
        @SerializedName("Lord Howe Standard Time")
        LORD_HOWE_STANDARD_TIME("Lord Howe Standard Time"),
        @SerializedName("Macquarie Island Standard Time")
        MACQUARIE_ISLAND_STANDARD_TIME("Macquarie Island Standard Time"),
        @SerializedName("Magadan Time")
        MAGADAN_TIME("Magadan Time"),
        @SerializedName("Malaysia Time")
        MALAYSIA_TIME("Malaysia Time"),
        @SerializedName("Maldives Time")
        MALDIVES_TIME("Maldives Time"),
        @SerializedName("Marquesas Time")
        MARQUESAS_TIME("Marquesas Time"),
        @SerializedName("Marshall Islands Time")
        MARSHALL_ISLANDS_TIME("Marshall Islands Time"),
        @SerializedName("Mauritius Time")
        MAURITIUS_TIME("Mauritius Time"),
        @SerializedName("Mawson Time")
        MAWSON_TIME("Mawson Time"),
        @SerializedName("Middle Europe Time")
        MIDDLE_EUROPE_TIME("Middle Europe Time"),
        @SerializedName("Moscow Standard Time")
        MOSCOW_STANDARD_TIME("Moscow Standard Time"),
        @SerializedName("Mountain Standard Time")
        MOUNTAIN_STANDARD_TIME("Mountain Standard Time"),
        @SerializedName("Myanmar Time")
        MYANMAR_TIME("Myanmar Time"),
        @SerializedName("Nauru Time")
        NAURU_TIME("Nauru Time"),
        @SerializedName("Nepal Time")
        NEPAL_TIME("Nepal Time"),
        @SerializedName("New Caledonia Time")
        NEW_CALEDONIA_TIME("New Caledonia Time"),
        @SerializedName("New Zealand Standard Time")
        NEW_ZEALAND_STANDARD_TIME("New Zealand Standard Time"),
        @SerializedName("Newfoundland Standard Time")
        NEWFOUNDLAND_STANDARD_TIME("Newfoundland Standard Time"),
        @SerializedName("Niue Time")
        NIUE_TIME("Niue Time"),
        @SerializedName("Norfolk Time")
        NORFOLK_TIME("Norfolk Time"),
        @SerializedName("Novosibirsk Time")
        NOVOSIBIRSK_TIME("Novosibirsk Time"),
        @SerializedName("Omsk Time")
        OMSK_TIME("Omsk Time"),
        @SerializedName("Oral Time")
        ORAL_TIME("Oral Time"),
        @SerializedName("Pacific Standard Time")
        PACIFIC_STANDARD_TIME("Pacific Standard Time"),
        @SerializedName("Pakistan Time")
        PAKISTAN_TIME("Pakistan Time"),
        @SerializedName("Palau Time")
        PALAU_TIME("Palau Time"),
        @SerializedName("Papua New Guinea Time")
        PAPUA_NEW_GUINEA_TIME("Papua New Guinea Time"),
        @SerializedName("Paraguay Time")
        PARAGUAY_TIME("Paraguay Time"),
        @SerializedName("Peru Time")
        PERU_TIME("Peru Time"),
        @SerializedName("Petropavlovsk-Kamchatski Time")
        PETROPAVLOVSK_KAMCHATSKI_TIME("Petropavlovsk-Kamchatski Time"),
        @SerializedName("Philippines Time")
        PHILIPPINES_TIME("Philippines Time"),
        @SerializedName("Phoenix Is. Time")
        PHOENIX_IS_TIME("Phoenix Is. Time"),
        @SerializedName("Pierre & Miquelon Standard Time")
        PIERRE_MIQUELON_STANDARD_TIME("Pierre & Miquelon Standard Time"),
        @SerializedName("Pitcairn Standard Time")
        PITCAIRN_STANDARD_TIME("Pitcairn Standard Time"),
        @SerializedName("Pohnpei Time")
        POHNPEI_TIME("Pohnpei Time"),
        @SerializedName("Qyzylorda Time")
        QYZYLORDA_TIME("Qyzylorda Time"),
        @SerializedName("Reunion Time")
        REUNION_TIME("Reunion Time"),
        @SerializedName("Rothera Time")
        ROTHERA_TIME("Rothera Time"),
        @SerializedName("Sakhalin Time")
        SAKHALIN_TIME("Sakhalin Time"),
        @SerializedName("Samara Time")
        SAMARA_TIME("Samara Time"),
        @SerializedName("Samoa Standard Time")
        SAMOA_STANDARD_TIME("Samoa Standard Time"),
        @SerializedName("Seychelles Time")
        SEYCHELLES_TIME("Seychelles Time"),
        @SerializedName("Singapore Time")
        SINGAPORE_TIME("Singapore Time"),
        @SerializedName("Solomon Is. Time")
        SOLOMON_IS_TIME("Solomon Is. Time"),
        @SerializedName("South Africa Standard Time")
        SOUTH_AFRICA_STANDARD_TIME("South Africa Standard Time"),
        @SerializedName("South Georgia Standard Time")
        SOUTH_GEORGIA_STANDARD_TIME("South Georgia Standard Time"),
        @SerializedName("Srednekolymsk Time")
        SREDNEKOLYMSK_TIME("Srednekolymsk Time"),
        @SerializedName("Suriname Time")
        SURINAME_TIME("Suriname Time"),
        @SerializedName("Syowa Time")
        SYOWA_TIME("Syowa Time"),
        @SerializedName("Tahiti Time")
        TAHITI_TIME("Tahiti Time"),
        @SerializedName("Tajikistan Time")
        TAJIKISTAN_TIME("Tajikistan Time"),
        @SerializedName("Timor-Leste Time")
        TIMOR_LESTE_TIME("Timor-Leste Time"),
        @SerializedName("Tokelau Time")
        TOKELAU_TIME("Tokelau Time"),
        @SerializedName("Tonga Time")
        TONGA_TIME("Tonga Time"),
        @SerializedName("Turkmenistan Time")
        TURKMENISTAN_TIME("Turkmenistan Time"),
        @SerializedName("Tuvalu Time")
        TUVALU_TIME("Tuvalu Time"),
        @SerializedName("Ulaanbaatar Time")
        ULAANBAATAR_TIME("Ulaanbaatar Time"),
        @SerializedName("Uruguay Time")
        URUGUAY_TIME("Uruguay Time"),
        @SerializedName("Ust-Nera Time")
        UST_NERA_TIME("Ust-Nera Time"),
        @SerializedName("Uzbekistan Time")
        UZBEKISTAN_TIME("Uzbekistan Time"),
        @SerializedName("Vanuatu Time")
        VANUATU_TIME("Vanuatu Time"),
        @SerializedName("Venezuela Time")
        VENEZUELA_TIME("Venezuela Time"),
        @SerializedName("Vladivostok Time")
        VLADIVOSTOK_TIME("Vladivostok Time"),
        @SerializedName("Vostok Time")
        VOSTOK_TIME("Vostok Time"),
        @SerializedName("Wake Time")
        WAKE_TIME("Wake Time"),
        @SerializedName("Wallis & Futuna Time")
        WALLIS_FUTUNA_TIME("Wallis & Futuna Time"),
        @SerializedName("West Indonesia Time")
        WEST_INDONESIA_TIME("West Indonesia Time"),
        @SerializedName("West Samoa Standard Time")
        WEST_SAMOA_STANDARD_TIME("West Samoa Standard Time"),
        @SerializedName("Western African Time")
        WESTERN_AFRICAN_TIME("Western African Time"),
        @SerializedName("Western European Time")
        WESTERN_EUROPEAN_TIME("Western European Time"),
        @SerializedName("Western Greenland Time")
        WESTERN_GREENLAND_TIME("Western Greenland Time"),
        @SerializedName("Xinjiang Standard Time")
        XINJIANG_STANDARD_TIME("Xinjiang Standard Time"),
        @SerializedName("Yakutsk Time")
        YAKUTSK_TIME("Yakutsk Time"),
        @SerializedName("Yekaterinburg Time")
        YEKATERINBURG_TIME("Yekaterinburg Time");
        private final String value;
        private final static Map<String, BaseProperties.Timezone> CONSTANTS = new HashMap<String, BaseProperties.Timezone>();

        static {
            for (BaseProperties.Timezone c: values()) {
                CONSTANTS.put(c.value, c);
            }
        }

        private Timezone(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }

        public String value() {
            return this.value;
        }

        public static BaseProperties.Timezone fromValue(String value) {
            BaseProperties.Timezone constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

}
