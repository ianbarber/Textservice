(defproject textapp "0.1.0-SNAPSHOT"
            :description "Simple text analysis rest service"
            :dependencies [[org.clojure/clojure "1.3.0"]
							[clojure-opennlp "0.1.9"]
                           [noir "1.2.1"]]
            :main textapp.server)

