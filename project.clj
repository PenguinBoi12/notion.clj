(defproject notion.clj/notion.clj "0.0.1-SNAPSHOT"
  :description "A clojure library for creating Notion"
  :url "https://github.com/penguinboi/notion.clj"
  :license {:name "Apache License, Version 2.0"
            :url "http://www.apache.org/licenses/LICENSE-2.0.html"}
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [org.clojure/core.async "1.5.648"]
                 [org.clojure/tools.cli "1.0.206"]
                 [org.clojure/data.json "0.2.6"]
                 [clj-http/clj-http "3.12.3"]
                 [clj-time/clj-time "0.14.4"]
                 [cheshire "5.10.2"]
                 [environ/environ.core "0.3.1"]
                 [integrant/repl "0.3.2"]
                 [nrepl/nrepl "0.8.3"]]
  :main ^:skip-aot notion.core
  :target-path "target/%s"
  :source-paths ["src"]
  :repl-options {:init-ns user}
  :profiles {:uberjar {:aot :all}
             :examples {:source-paths ["examples"]}})
