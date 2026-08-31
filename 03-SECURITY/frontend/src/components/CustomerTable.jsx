
import { useEffect, useState } from "react";

import api from "../api/axios";

import {
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableRow,
  Button,
  TextField,
} from "@mui/material";

import CustomerModal from "./CustomerModal";
import { useSnackbar } from "notistack";

function CustomerTable() {
  const [data, setData] = useState([]);
  const [search, setSearch] = useState("");
  const [open, setOpen] = useState(false);
  const [edit, setEdit] = useState(null);

  const { enqueueSnackbar } = useSnackbar();

  // =========================
  // FETCH CUSTOMERS
  // =========================
  const fetchData = async () => {
    try {
      const res = await api.get("/api/v1/customers");

      setData(res.data);
    } catch (error) {
      console.error("Failed to fetch customers:", error);

      if (error.response?.status === 401) {
        enqueueSnackbar("Session expired. Please login again.", {
          variant: "error",
        });
      } else {
        enqueueSnackbar("Failed to load customers", {
          variant: "error",
        });
      }
    }
  };

  // Fetch customers when component loads
  useEffect(() => {
    fetchData();
  }, []);

  // =========================
  // CREATE / UPDATE CUSTOMER
  // =========================
  const handleSave = async (form) => {
    try {
      if (edit) {
        // UPDATE
        await api.put(`/api/v1/customers/${edit.id}`, form);

        enqueueSnackbar("Updated successfully", {
          variant: "success",
        });
      } else {
        // CREATE
        await api.post("/api/v1/customers", form);

        enqueueSnackbar("Created successfully", {
          variant: "success",
        });
      }

      // Close modal
      setOpen(false);
      setEdit(null);

      // Refresh customer list
      await fetchData();
    } catch (error) {
      console.error("Customer save failed:", error);

      if (error.response?.status === 401) {
        enqueueSnackbar("Unauthorized. Please login again.", {
          variant: "error",
        });
      } else {
        enqueueSnackbar("Operation failed", {
          variant: "error",
        });
      }
    }
  };

  // =========================
  // DELETE CUSTOMER
  // =========================
  const handleDelete = async (id) => {
    try {
      await api.delete(`/api/v1/customers/${id}`);

      enqueueSnackbar("Deleted", {
        variant: "success",
      });

      await fetchData();
    } catch (error) {
      console.error("Customer delete failed:", error);

      if (error.response?.status === 401) {
        enqueueSnackbar("Unauthorized. Please login again.", {
          variant: "error",
        });
      } else {
        enqueueSnackbar("Delete failed", {
          variant: "error",
        });
      }
    }
  };

  // =========================
  // SEARCH
  // =========================
  const filtered = data.filter((c) =>
    c.name?.toLowerCase().includes(search.toLowerCase())
  );

  return (
    <>
      {/* SEARCH */}
      <TextField
        label="Search"
        value={search}
        onChange={(e) => setSearch(e.target.value)}
      />

      {/* ADD CUSTOMER */}
      <Button
        onClick={() => {
          setEdit(null);
          setOpen(true);
        }}
      >
        Add Customer
      </Button>

      {/* CUSTOMER TABLE */}
      <Table>
        <TableHead>
          <TableRow>
            <TableCell>ID</TableCell>
            <TableCell>Name</TableCell>
            <TableCell>Address</TableCell>
            <TableCell>Gender</TableCell>
            <TableCell>Actions</TableCell>
          </TableRow>
        </TableHead>

        <TableBody>
          {filtered.map((c) => (
            <TableRow key={c.id}>
              <TableCell>{c.id}</TableCell>

              <TableCell>{c.name}</TableCell>

              <TableCell>{c.address}</TableCell>

              <TableCell>{c.gender}</TableCell>

              <TableCell>
                {/* EDIT */}
                <Button
                  onClick={() => {
                    setEdit(c);
                    setOpen(true);
                  }}
                >
                  Edit
                </Button>

                {/* DELETE */}
                <Button
                  color="error"
                  onClick={() => handleDelete(c.id)}
                >
                  Delete
                </Button>
              </TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>

      {/* CUSTOMER MODAL */}
      <CustomerModal
        open={open}
        handleClose={() => {
          setOpen(false);
          setEdit(null);
        }}
        handleSave={handleSave}
        editData={edit}
      />
    </>
  );
}

export default CustomerTable;

