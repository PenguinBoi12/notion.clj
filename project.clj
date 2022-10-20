(defproject notion.clj/notion.clj "1.0.0-SNAPSHOT"
  :description "A notion api wrapper in Clojure"
  :url "https://github.com/penguinboi/notion.clj"
  :license {:name "GNU General Public License v3.0"
            :url "https://www.gnu.org/licenses/gpl-3.0.html"}
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [org.clojure/core.async "1.5.648"]
                 [org.clojure/tools.cli "1.0.206"]
                 [org.clojure/data.json "0.2.6"]
                 [clj-http/clj-http "3.12.3"]
                 [clj-time/clj-time "0.14.4"]
                 [cheshire "5.11.0"]
                 [integrant/repl "0.3.2"]
                 [nrepl/nrepl "0.8.3"]
                 [slingshot "0.12.2"]
                 [com.taoensso/timbre "5.2.1"]
                 [camel-snake-kebab "0.4.3"]]
  :main ^:skip-aot notion.core
  :target-path "target/%s"
  :source-paths ["src"]
  :profiles {:uberjar {:aot :all}
             :examples {:source-paths ["examples"]}})
