It's a grails plugin to implement ActsAsTaggable.
Just let your domain class implement Taggable
interface, your domain class will be taggable.

Usage:

  1. download grails-acts-as-taggable-0.1.zip
  1. grails install-plugin grails-acts-as-taggable-0.1.zip
  1. Add implments Taggable
  1. Done.

Notice:
  * You should save your domain object before call the addTag method.
  * More information: http://grails.org/Acts+As+Taggable+Plugin