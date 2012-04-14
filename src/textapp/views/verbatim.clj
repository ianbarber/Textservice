(ns textapp.views.verbatim
  (:require [textapp.views.common :as common]
		[noir.response :as resp])
  (:use [noir.core]
		[hiccup.core]
		[hiccup.form-helpers]
		[opennlp.nlp]
		[clojure.string :only [blank?]]))

(def tokeniser (make-tokenizer "resources/en-token.bin"))
(def tagger (make-pos-tagger "resources/en-pos-maxent.bin"))

(defpartial verbatim-fields [{:keys [text]}]
  (text-field {:placeholder "Text to analyse: "} :text text))

(defpage "/" {:as verbatim}
  (common/layout
    (form-to [:post "/verbatim"]
            (verbatim-fields verbatim)
            (submit-button "Analyse"))))

(defpage [:post "/verbatim"] {text :text :or {text ""}}
	(if (blank? text)
		(resp/json {:error "Invalid Input"})
		(resp/json {:tagged (tagger (tokeniser text)) } )))
