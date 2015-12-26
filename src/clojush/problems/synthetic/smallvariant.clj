;; smallvariant.clj
;; A problem for Clojush, a Push/PushGP system written in Clojure
;; Lee Spector, lspector@hampshire.edu, 2015

;; NOTE: "Success" is not possible on this problem, unless a non-default
;; error threshold is used.

;; This problem exists to evolve small genomes that are recursively variant
;; in the context of autoconstruction. The single error value is the size
;; of the genome + 1 if the genome is recursively variant, or a large
;; penalty otherwise.

(ns clojush.problems.synthetic.smallvariant
  (:use [clojush.pushgp.pushgp]
        [clojush.util]
        [clojush.pushgp.genetic-operators]))

(defn smallvariant-error
  "Returns a vector containing a single error which will be the 
  size of the genome + 1 if the genome is recursively variant, or a large
  penalty otherwise." 
  [individual]
  (if (recursively-variant? (:genome individual) @push-argmap)
    [(inc (count (:genome individual)))]
    [1000000]))

(def argmap
  {:error-function smallvariant-error
   :atom-generators []
   :autoconstructive true
   :report-simplifications 0
   :pass-individual-to-error-function true
   :max-points 500
   :evalpush-limit 1000})

;(reset-globals argmap)
;
;(recursively-variant?
;  '({:close 0, :silent false, :instruction integer_dec} 
;     {:close 0, :silent true, :instruction boolean_or} 
;     {:close 0, :silent false, :instruction exec_noop} 
;     {:close 1, :silent false, :instruction genome_stackdepth} 
;     {:close 0, :silent false, :instruction integer_empty} 
;     {:close 1, :silent false, :instruction exec_dup} 
;     {:close 1, :silent false, :instruction exec_pop} 
;     {:close 1, :silent false, :instruction integer_fromboolean} 
;     {:close 0, :silent true, :instruction integer_flush} 
;     {:close 1, :silent false, :instruction genome_gene_copy_range} 
;     {:close 0, :silent false, :instruction exec_dup} 
;     {:close 0, :silent false, :instruction exec_while} 
;     {:close 0, :silent false, :instruction integer_pop} 
;     {:close 0, :silent true, :instruction boolean_shove} 
;     {:close 0, :silent false, :instruction autoconstructive_integer_rand} 
;     {:close 0, :silent true, :instruction genome_gene_delete} 
;     {:close 1, :silent false, :instruction exec_yankdup} 
;     {:close 0, :silent false, :instruction genome_gene_randomize} 
;     {:close 0, :silent false, :instruction genome_gene_delete} 
;     {:close 0, :silent false, :instruction exec_stackdepth} 
;     {:close 1, :silent false, :instruction genome_gene_delete} 
;     {:close 0, :silent false, :instruction exec_do*count} 
;     {:close 0, :silent false, :instruction integer_lt} 
;     {:close 0, :silent false, :instruction exec_empty} 
;     {:close 0, :silent true, :instruction genome_rotate} 
;     {:close 0, :silent false, :instruction boolean_pop} 
;     {:close 0, :silent false, :instruction exec_shove} 
;     {:close 0, :silent false, :instruction integer_lte})
;  @push-argmap)


