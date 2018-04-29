package com.theanhdev97.tintucbongda.screen.model;

/**
 * Created by DELL on 29/04/2018.
 */

public class News {
  private String title;
  private String link;
  private String image;
  private String pubDate;
  private String description;

  public News() {
  }

  public News(String title, String link, String image, String pubDate, String description) {

    this.title = title;
    this.link = link;
    this.image = image;
    this.pubDate = pubDate;
    this.description = description;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getLink() {
    return link;
  }

  public void setLink(String link) {
    this.link = link;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public String getPubDate() {
    return pubDate;
  }

  public void setPubDate(String pubDate) {
    this.pubDate = pubDate;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }


}
