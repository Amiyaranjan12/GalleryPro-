package com.example.gallerypro.Search.OtherClass;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;





public class SearchModel {

    @JsonProperty("photos")
    private Photos photos;
    @JsonProperty("stat")
    private String stat;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("photos")
    public Photos getPhotos() {
        return photos;
    }

    @JsonProperty("photos")
    public void setPhotos(Photos photos) {
        this.photos = photos;
    }

    @JsonProperty("stat")
    public String getStat() {
        return stat;
    }

    @JsonProperty("stat")
    public void setStat(String stat) {
        this.stat = stat;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
    public class Photos {

        @JsonProperty("page")
        private Integer page;
        @JsonProperty("pages")
        private Integer pages;
        @JsonProperty("perpage")
        private Integer perpage;
        @JsonProperty("total")
        private Integer total;
        @JsonProperty("photo")
        private List<Photo> photo = null;
        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        @JsonProperty("page")
        public Integer getPage() {
            return page;
        }

        @JsonProperty("page")
        public void setPage(Integer page) {
            this.page = page;
        }

        @JsonProperty("pages")
        public Integer getPages() {
            return pages;
        }

        @JsonProperty("pages")
        public void setPages(Integer pages) {
            this.pages = pages;
        }

        @JsonProperty("perpage")
        public Integer getPerpage() {
            return perpage;
        }

        @JsonProperty("perpage")
        public void setPerpage(Integer perpage) {
            this.perpage = perpage;
        }

        @JsonProperty("total")
        public Integer getTotal() {
            return total;
        }

        @JsonProperty("total")
        public void setTotal(Integer total) {
            this.total = total;
        }

        @JsonProperty("photo")
        public List<Photo> getPhoto() {
            return photo;
        }

        @JsonProperty("photo")
        public void setPhoto(List<Photo> photo) {
            this.photo = photo;
        }

        @JsonAnyGetter
        public Map<String, Object> getAdditionalProperties() {
            return this.additionalProperties;
        }

        @JsonAnySetter
        public void setAdditionalProperty(String name, Object value) {
            this.additionalProperties.put(name, value);
        }
        public class Photo {

            @JsonProperty("id")
            private String id;
            @JsonProperty("owner")
            private String owner;
            @JsonProperty("secret")
            private String secret;
            @JsonProperty("server")
            private String server;
            @JsonProperty("farm")
            private Integer farm;
            @JsonProperty("title")
            private String title;
            @JsonProperty("ispublic")
            private Integer ispublic;
            @JsonProperty("isfriend")
            private Integer isfriend;
            @JsonProperty("isfamily")
            private Integer isfamily;
            @JsonProperty("url_s")
            private String url_s;
            @JsonProperty("height_s")
            private Integer heightS;
            @JsonProperty("width_s")
            private Integer widthS;
            @JsonIgnore
            private Map<String, Object> additionalProperties = new HashMap<String, Object>();

            @JsonProperty("id")
            public String getId() {
                return id;
            }

            @JsonProperty("id")
            public void setId(String id) {
                this.id = id;
            }

            @JsonProperty("owner")
            public String getOwner() {
                return owner;
            }

            @JsonProperty("owner")
            public void setOwner(String owner) {
                this.owner = owner;
            }

            @JsonProperty("secret")
            public String getSecret() {
                return secret;
            }

            @JsonProperty("secret")
            public void setSecret(String secret) {
                this.secret = secret;
            }

            @JsonProperty("server")
            public String getServer() {
                return server;
            }

            @JsonProperty("server")
            public void setServer(String server) {
                this.server = server;
            }

            @JsonProperty("farm")
            public Integer getFarm() {
                return farm;
            }

            @JsonProperty("farm")
            public void setFarm(Integer farm) {
                this.farm = farm;
            }

            @JsonProperty("title")
            public String getTitle() {
                return title;
            }

            @JsonProperty("title")
            public void setTitle(String title) {
                this.title = title;
            }

            @JsonProperty("ispublic")
            public Integer getIspublic() {
                return ispublic;
            }

            @JsonProperty("ispublic")
            public void setIspublic(Integer ispublic) {
                this.ispublic = ispublic;
            }

            @JsonProperty("isfriend")
            public Integer getIsfriend() {
                return isfriend;
            }

            @JsonProperty("isfriend")
            public void setIsfriend(Integer isfriend) {
                this.isfriend = isfriend;
            }

            @JsonProperty("isfamily")
            public Integer getIsfamily() {
                return isfamily;
            }

            @JsonProperty("isfamily")
            public void setIsfamily(Integer isfamily) {
                this.isfamily = isfamily;
            }

            @JsonProperty("url_s")
            public String getUrlS() {
                return url_s;
            }

            @JsonProperty("url_s")
            public void setUrlS(String urlS) {
                this.url_s = urlS;
            }

            @JsonProperty("height_s")
            public Integer getHeightS() {
                return heightS;
            }

            @JsonProperty("height_s")
            public void setHeightS(Integer heightS) {
                this.heightS = heightS;
            }

            @JsonProperty("width_s")
            public Integer getWidthS() {
                return widthS;
            }

            @JsonProperty("width_s")
            public void setWidthS(Integer widthS) {
                this.widthS = widthS;
            }

            @JsonAnyGetter
            public Map<String, Object> getAdditionalProperties() {
                return this.additionalProperties;
            }

            @JsonAnySetter
            public void setAdditionalProperty(String name, Object value) {
                this.additionalProperties.put(name, value);
            }

        }
    }

}


