(ns notion.types.utils
  (:require [clojure.set :refer [rename-keys]]))

(defn format-type-map [m to-rename-keys]
  (-> m
    (select-keys (keys to-rename-keys))
    (rename-keys to-rename-keys)))
