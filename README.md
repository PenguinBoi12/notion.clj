# notion.clj
This is a Notion API wrapper written in Clojure. The goal is to create an easy, clean and complete way to communicate with [Notion](notion.so) through
their [API](https://developers.notion.com/).

# Getting started
### Installation
For now, the library is only available through github or locally with [Clojure deps.edn](https://clojure.org/guides/deps_and_cli). 

#### Locally 
```edn
{:deps
  {io.github.PenguinBoi12/notion.clj {:root/local "path/to/library/notion.clj"}}}
```

#### Clojure deps with github
```edn
{:deps
  {io.github.PenguinBoi12/notion.clj {:git/tag "stable" :git/sha "e294812"}}}
```


### Configuration
- For this part, follow [Notion's guide](https://developers.notion.com/docs/getting-started#getting-started).
- Once you have you your [integration](https://www.notion.so/my-integrations) setup, create a `resources` folder and a config file named `config.edn` then add `{:token "You token here"}` into it.

# Example(s)
### Retrive a user
```clojure
(ns my-notion-project.core
  (:require notion.client :as client))

(defn -main [& args]
  (let [client (client/init!)
        user (client/user client "user-id")]
    (println user))))
```

# Contributing
Everyone is welcome to participate in the development of the library. You can contribute to the project by simply opening an issue, by improving some
current features or even by adding your own. If you want to contribute but don't have any idea on what to work on, you can checkout out
[issues](https://github.com/PenguinBoi12/notion.clj/issues) page.

# License
Distributed under the GNU General Public License v3.0
