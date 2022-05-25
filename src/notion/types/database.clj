(ns notion.types.database
  (:require [clojure.set :refer [rename-keys]]))

(defonce database-keys {:id :id
                        :title :title
                        :icon :icon
                        :cover :cover
                        :parent :parent
                        :properties :properties
                        :archived :archived?
                        :created_time :created-time
                        :created_by :created-by
                        :last_edited_time :last-edited-time
                        :last_edited_by :last-edited-by})

(defrecord Database [id title icon cover parent archived? created-time
                     created-by last-edited-time last-edited-by url])

(defn build-database [m]
  (rename-keys m database-keys))
