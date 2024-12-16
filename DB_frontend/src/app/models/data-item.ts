export interface Transaction {
  id?: string;
  name: string;
  unit: string;
  coefficient: number;
  category: string;
  source: string;
  source_name: string;
}

export interface Category {
  id?: string;
  name: string;
  parent: string;
}

export interface HSRStation {
  id?: string;
  origin: string;
  destination: string;
  carbonFootprint: number;
} 