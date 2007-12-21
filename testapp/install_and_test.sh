cd ../ActsAsTaggable/
grails package-plugin
cd ../testapp
grails install-plugin ../ActsAsTaggable/grails-acts-as-taggable-0.1.zip
grails test-app
